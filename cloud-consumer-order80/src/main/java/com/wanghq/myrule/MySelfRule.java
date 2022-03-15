package com.wanghq.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-21 21:40
 */
@Configuration
public class MySelfRule {

    //替换Ribbon的默认轮询算法，使用随机算法
    @Bean
    public IRule myselefRule(){
        return new RandomRule();//定义为随机算法
    }
}
