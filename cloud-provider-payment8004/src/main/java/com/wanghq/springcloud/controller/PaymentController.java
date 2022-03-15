package com.wanghq.springcloud.controller;

import com.wanghq.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-20 11:14
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/zk")
    public CommonResult testZk(){
        return new CommonResult(200,"springcloud with zookeeper:"+ serverPort + "  " + UUID.randomUUID().toString());
    }
}
