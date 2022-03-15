package com.wanghq.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wanghq.springcloud.service.PaymentServer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-25 11:11
 */
@Service
public class PaymentServerImpl implements PaymentServer {
    /*===============服务降级========================*/
    /**
     * @param id
     * @description  正常访问，一切OK
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池"+ Thread.currentThread().getName() + "  paymentInfo_OK,id: " +id +"\t" + "O(∩_∩)O";
    }
    /**
     * @param id
     * @description  演示超时访问，服务降级
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        int second = 3;
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+ Thread.currentThread().getName() + "  paymentInfo_TimeOut,id: " +id +"\t" + "耗时（秒）："+ second;
    }
    //超时兜底方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "系统繁忙或运行报错(方法：paymentInfo_TimeOut)，请稍后再试, id: " +"\t" +id +"\t" + "/(ㄒoㄒ)/~~";
    }

    /**
     * @param id
     * @description 演示服务出现异常，服务降级
     * @author whq
     * @create 2021/12/26 10:57
     * @return java.lang.String
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_ExceptionHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    @Override
    public String paymentInfo_Exception(Integer id) {
        int i = 1/0;
        return "线程池"+ Thread.currentThread().getName() + "  paymentInfo_Exception,id: " +id +"\t" ;
    }
    //异常兜底方法
    public String paymentInfo_ExceptionHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "系统繁忙或运行报错(方法：paymentInfo_Exception)，请稍后再试, id: " +"\t" +id +"\t" + "/(ㄒoㄒ)/~~";
    }



    /*===============服务熔断========================*/

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路由
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //时间窗口期最少请求总数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        //hutool工具类jar包，相当于UUID.randomUUID().toString()的结果去掉 -
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }







}
