package com.wanghq.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-23 14:39
 */
@SpringBootApplication
@EnableFeignClients //开启Feign
public class OrderOpenFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain80.class,args);
    }
}
