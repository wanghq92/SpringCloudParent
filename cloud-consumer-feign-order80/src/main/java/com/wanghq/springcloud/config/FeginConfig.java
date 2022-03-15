package com.wanghq.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-24 16:10
 */
@Configuration
public class FeginConfig {

    @Bean
    public Logger.Level feginLoggerLevel(){
        //openFegin 的日志级别
        return Logger.Level.FULL;
    }
}
