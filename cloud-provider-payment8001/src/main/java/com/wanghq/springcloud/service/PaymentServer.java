package com.wanghq.springcloud.service;

import com.wanghq.springcloud.entities.Payment;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 15:48
 */
public interface PaymentServer {
   public int create(Payment payment);

   public Payment getPaymentById(Long id);
}
