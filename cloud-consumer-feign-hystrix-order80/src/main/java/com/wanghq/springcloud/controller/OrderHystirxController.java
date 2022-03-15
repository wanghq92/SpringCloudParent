package com.wanghq.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wanghq.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-25 12:15
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    /***
     * @param id
     * @description 客户端演示超时访问，服务降级
     * @author whq
     * @create 2021/12/26 12:00
     * @return java.lang.String
     */
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_ExceptionHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="2000")
//    })
    @HystrixCommand //加了@DefaultProperties属性注解，并且没有写具体方法名字，就用统一全局的
    @GetMapping("/consumer/hystrix/payment/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    //超时、异常兜底方法
    public String paymentInfo_TimeOut_ExceptionHandler(@PathVariable("id")Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    /**
     * @param id
     * @description  客户端演示访问异常，服务降级
     * @author whq
     * @create 2021/12/26 12:11
     * @return java.lang.String
     */
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_ExceptionHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
    @HystrixCommand //加了@DefaultProperties属性注解，并且没有写具体方法名字，就用统一全局的
    @GetMapping("/consumer/hystrix/payment/exception/{id}")
    public String paymentInfo_Exception(@PathVariable("id")Integer id){
//        int i = 10/0;
        return paymentHystrixService.paymentInfo_Exception(id);
    }

    //全局global fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
