package com.wanghq.springcloud.controller;

import com.wanghq.springcloud.entities.CommonResult;
import com.wanghq.springcloud.entities.Payment;
import com.wanghq.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author whq
 * @version 1.0.0
 * @Description 客户端，模拟客户提交支付单 查询支付单
 * @createTime 2021-12-16 20:36
 */
@RestController
@Slf4j
public class OrderController {

//    private static final String URL = "http://localhost:8001"; //微服务提供者单机版 写死url
    // 通过在eureka上注册过的微服务名称调用
    private static final String URL = "http://CLOUD-PAYMENT-SERVICE"; //微服务提供者集群版

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalance loadBalance;

    //模拟浏览器只能发get请求
    @GetMapping("/consumer/create")
    public CommonResult create( Payment payment){

        return restTemplate.postForObject(URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return restTemplate.getForObject(URL+"/payment/get/"+id,CommonResult.class);

    }

    @GetMapping("/consumer/discovery")
    public Object discovery(){
        return restTemplate.getForObject(URL+"/payment/discovery",Object.class);
    }

    //返回对象为响应体中数据转化成的对象，基本上可以理解为Json
    @GetMapping("/consumer/getForEntity/{id}")
    public CommonResult getForEntity(@PathVariable("id") String id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(URL+"/payment/get/"+id, CommonResult.class);
        log.info(entity.getStatusCode()+ "\t" +entity.getHeaders());
        //
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult(200,"请求失败");
        }

    }
    //返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
    @PostMapping("/consumer/create/postForEntity")
    public CommonResult postForEntity(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(URL + "/payment/create", payment, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult(200,"请求失败" );
        }
    }

    //测试手写一个负载均衡算法
    @GetMapping("/consumer/lb")
    public String testLB(){
        //查询某个微服务的所有服务实例
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(serviceInstances == null || serviceInstances.size()<=0){
            return null;
        }
        //调用手写的轮询负载算法，返回具体微服务实例
        ServiceInstance instance = loadBalance.getIndex(serviceInstances);

        URI uri = instance.getUri();

        return restTemplate.getForObject(uri+"payment/lb",String.class);
    }


    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}
