package com.wanghq.springcloud.service;

import com.wanghq.springcloud.entities.CommonResult;
import com.wanghq.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-23 14:42
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") //使用openFeign,值为注册到eureka的微服务名称
public interface PaymentFeignService {

    //注解、value值和方法声明，和服务提供者的Controller的方法声明相同
    @GetMapping(value="/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/fegin/timeout")
    public String testFeginTimeOut();
}
