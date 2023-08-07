package com.mx.service.service.impl;

import com.mx.service.annotation.RedissonLock;
import com.mx.service.annotation.ReetrantLock;
import com.mx.service.core.R;
import com.mx.service.mapper.TOrderPayDao;
import com.mx.service.entity.TOrderPay;
import com.mx.service.service.TOrderPayService;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * (TOrderPay)表服务实现类
 *
 * @author mx
 * @since 2023-08-02 16:07:59
 */
@Service
public class TOrderPayServiceImpl implements TOrderPayService {
    @Autowired
    private TOrderPayDao tOrderPayDao;



    private static Lock lock = new ReentrantLock();

    /**
     * 新增数据
     *
     * @param tOrderPay 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderPay insert(TOrderPay tOrderPay) {
        this.tOrderPayDao.insert(tOrderPay);
        return tOrderPay;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return true;
    }

    @Override
    @ReetrantLock
    @RedissonLock(key = "#req?.data?.id",waitTime = 20)
    @Transactional(rollbackFor = Exception.class)
    public R<TOrderPay> nextPay(R<TOrderPay> req) {
        R<TOrderPay> tOrderPayR = new R<>();
        TOrderPay data = req.getData();

        // 1.查询是否库存充足

        if (!veryBuy("1")) {
            throw new RuntimeException("库存不足", new Throwable("库存不足"));
        }


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2. 扣减库存


            try {
                TOrderPay tOrderPay = new TOrderPay();
                tOrderPay.setId("1");
                tOrderPayDao.updateByIdRemain(tOrderPay);
            } catch (Exception e) {
                e.printStackTrace();
            }
            R<TOrderPay> data1 = R.data(data);
        return data1;
    }

    private boolean veryBuy(String id) {
        final int i = tOrderPayDao.veryBuy(id);
        if (tOrderPayDao.veryBuy(id) ==1){
            return true;
        }
        return false;
    }

}
