package com.mx.service.controller;

import com.mx.service.annotation.FrequencyControl;
import com.mx.service.core.R;
import com.mx.service.entity.TOrderPay;
import com.mx.service.service.TOrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (TOrderPay)表控制层
 *
 * @author mx
 * @since 2023-08-02 16:07:52
 */
@RestController
@RequestMapping("tOrderPay")
public class TOrderPayController {
    /**
     * 服务对象
     */
    @Autowired
    private TOrderPayService tOrderPayService;

    @RequestMapping("/pay")
    public R<TOrderPay> nextPay(@RequestBody R<TOrderPay> req){

        return tOrderPayService.nextPay(req);
    }
}

