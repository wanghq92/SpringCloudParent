package com.wanghq.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-22 22:01
 */
public interface LoadBalance {
    ServiceInstance getIndex(List<ServiceInstance> list);
}
