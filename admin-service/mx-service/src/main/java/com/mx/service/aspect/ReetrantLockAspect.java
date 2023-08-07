package com.mx.service.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: mx-service
 * @Package: com.mx.service.aspect
 * @Description: note
 * @Author: changfx43807
 * @CreateDate: 2023/8/7 10:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright © 2023 Hundsun Technologies Inc. All Rights Reserved
 **/
@Order(1)
@Aspect
@Component
public class ReetrantLockAspect {


    private static Lock lock = new ReentrantLock();


    @SneakyThrows
    @Around("@annotation(com.mx.service.annotation.ReetrantLock)")
    public Object doAround(ProceedingJoinPoint pjp) {
        // 加锁
        lock.lock();
        try {

            return pjp.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
