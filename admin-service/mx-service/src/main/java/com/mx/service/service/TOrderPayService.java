package com.mx.service.service;

import com.mx.service.core.R;
import com.mx.service.entity.TOrderPay;
/**
 * (TOrderPay)表服务接口
 *
 * @author mx
 * @since 2023-08-02 16:07:59
 */
public interface TOrderPayService {


    /**
     * 新增数据
     *
     * @param tOrderPay 实例对象
     * @return 实例对象
     */
    TOrderPay insert(TOrderPay tOrderPay);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    R<TOrderPay> nextPay(R<TOrderPay> req);
}
