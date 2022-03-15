package com.wanghq.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 20:52
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced  //注释掉，使用自定义的负载算法 //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate restTemplate(){
        return new  RestTemplate();
    }
}
