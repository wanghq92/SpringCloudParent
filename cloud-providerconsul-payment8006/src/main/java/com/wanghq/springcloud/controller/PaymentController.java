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
 * @createTime 2021-12-20 17:19
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/consul")
    public CommonResult testConsul(){
        return new CommonResult(200,"springcloud with consul:"+ serverPort + "  " + UUID.randomUUID().toString());
    }
}
