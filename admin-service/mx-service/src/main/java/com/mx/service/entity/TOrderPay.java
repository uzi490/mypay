package com.mx.service.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (TOrderPay)实体类
 *
 * @author mx
 * @since 2023-08-02 16:07:55
 */
@Data
@Table(name = "t_order_pay")
public class TOrderPay implements Serializable {
    private static final long serialVersionUID = -88171626099581772L;

    @Id
    private String id;

    @Column(name ="total")
    private Long total;

    @Column(name = "remain")
    private Long remain;



}

