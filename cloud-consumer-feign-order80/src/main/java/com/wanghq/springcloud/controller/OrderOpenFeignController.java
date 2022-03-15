package com.wanghq.springcloud.controller;

import com.wanghq.springcloud.entities.CommonResult;
import com.wanghq.springcloud.entities.Payment;
import com.wanghq.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-23 14:47
 */
@RestController
public class OrderOpenFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    /**
     * Feign 自带负载均衡配置项
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id ){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String testFeginTimeOut(){
        return paymentFeignService.testFeginTimeOut();
    }
}
