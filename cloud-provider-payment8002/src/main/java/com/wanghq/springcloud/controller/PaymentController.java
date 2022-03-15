package com.wanghq.springcloud.controller;

import com.wanghq.springcloud.entities.CommonResult;
import com.wanghq.springcloud.entities.Payment;
import com.wanghq.springcloud.service.PaymentServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 15:53
 */
@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentServer paymentServer;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentServer.create(payment);
        //记录日志
        log.info("*** 插入操作返回结果"+ i );
        if(i>0){
            return new CommonResult(200,"插入数据库成功,服务端口号：" +serverPort,i);

        }else{
            return new CommonResult(444,"插入数据库失败,服务端口号：" +serverPort,null);
        }
    }

    @ResponseBody
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentServer.getPaymentById(id);
        log.info("**** 查询结果：" + payment);

        if(payment != null){
            return new CommonResult(200,"查询成功,服务端口号：" +serverPort,payment);

        }else{
            return new CommonResult(444,"没有对应记录，查询id:"+id +" 服务端口号：" +serverPort,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String applicaion: services) {
            log.info("*******Eureka Server包含的微服务："+applicaion);

            List<ServiceInstance> instances = discoveryClient.getInstances(applicaion);
            for (ServiceInstance instance: instances ) {
                log.info("     #####微服务实例信息："+instance.getServiceId()+"\t " + instance.getHost() + "\t"
                        + instance.getPort() +"\t" +instance.getUri());
            }
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public String testLB(){
        return serverPort;
    }

    @GetMapping("/payment/fegin/timeout")
    public String testFeginTimeOut(){

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
