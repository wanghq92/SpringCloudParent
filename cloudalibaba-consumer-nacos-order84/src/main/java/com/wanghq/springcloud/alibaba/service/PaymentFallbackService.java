package com.wanghq.springcloud.alibaba.service;

import com.wanghq.springcloud.entities.CommonResult;
import com.wanghq.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-13 11:24
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        Payment payment = new Payment(id, "errorSerial......");
        return new CommonResult<>(444,"服务降级返回,没有该流水信息",payment);
    }
}
