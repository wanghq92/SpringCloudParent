package com.wanghq.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author whq
 * @version 1.0.0
 * @Description 相当于mybatis.xml配置文件
 * @createTime 2022-01-17 23:03
 */
@Configuration
@MapperScan({"com.wanghq.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
