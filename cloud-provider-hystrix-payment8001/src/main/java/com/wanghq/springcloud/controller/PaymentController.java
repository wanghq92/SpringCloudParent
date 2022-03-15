package com.wanghq.springcloud.controller;

import com.wanghq.springcloud.service.PaymentServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-25 11:15
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentServer paymentServer;
    /**==================服务降级==================*/
    //正常访问，一切OK
    @GetMapping("/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentServer.paymentInfo_OK(id);
        log.info("*******result"+result);
        return result;
    }
    //超时访问，服务降级
    @GetMapping("/payment/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String result = paymentServer.paymentInfo_TimeOut(id);
        log.info("*******result"+result);
        return result;
    }

    //演示服务异常，服务降级
    @GetMapping("/payment/exception/{id}")
    public String paymentInfo_Exception(@PathVariable("id")Integer id){
        String result = paymentServer.paymentInfo_Exception(id);
        log.info("*******result"+result);
        return result;
    }
    /**==================服务熔断==================*/
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentServer.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }
}
