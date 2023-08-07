package com.mx.service.mapper;

import com.mx.service.entity.TOrderPay;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * (TOrderPay)表数据库访问层
 *
 * @author mx
 * @since 2023-08-02 16:07:52
 */
@Mapper
public interface TOrderPayDao extends BaseMapper<TOrderPay> {


    int veryBuy(String id);

    void updateByIdRemain(TOrderPay tOrderPay);
}

