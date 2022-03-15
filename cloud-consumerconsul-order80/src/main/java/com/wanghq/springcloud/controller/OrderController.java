package com.wanghq.springcloud.controller;

import com.wanghq.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-20 21:48
 */
@RestController
@Slf4j
public class OrderController {

    private static final String URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public CommonResult testConsul(){
        return restTemplate.getForObject(URL+"/payment/consul",CommonResult.class);
    }
}
