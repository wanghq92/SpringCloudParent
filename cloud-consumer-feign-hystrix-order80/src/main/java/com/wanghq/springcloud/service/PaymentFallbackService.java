package com.wanghq.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-26 17:42
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务调用失败，请稍后再试：paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务调用失败，请稍后再试：paymentInfo_TimeOut";
    }

    @Override
    public String paymentInfo_Exception(Integer id) {
        return "服务调用失败，请稍后再试：paymentInfo_Exception";
    }
}
