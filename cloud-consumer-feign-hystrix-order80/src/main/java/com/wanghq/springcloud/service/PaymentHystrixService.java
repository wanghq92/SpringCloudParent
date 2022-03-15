package com.wanghq.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-25 12:13
 */
@FeignClient(value="CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback=PaymentFallbackService.class)
@Component
public interface PaymentHystrixService {

    @GetMapping("/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id);

    //演示服务异常，服务降级
    @GetMapping("/payment/exception/{id}")
    public String paymentInfo_Exception(@PathVariable("id")Integer id);
}
