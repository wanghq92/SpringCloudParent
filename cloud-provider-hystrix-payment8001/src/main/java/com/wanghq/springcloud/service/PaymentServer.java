package com.wanghq.springcloud.service;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-25 11:09
 */
public interface PaymentServer {

    String paymentInfo_OK(Integer id);

    String paymentInfo_TimeOut(Integer id);

    String paymentInfo_Exception(Integer id);

    String paymentCircuitBreaker(Integer id);
}
