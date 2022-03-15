package com.wanghq.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whq
 * @version 1.0.0
 * @Description 手写一个 轮询 负载均衡算法
 * @createTime 2021-12-22 22:07
 */

@Component
public class MyLoadBalanace implements LoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    //自旋锁 高并发（多线程）环境下,保证用户请求 计数器 按顺序增长
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current >Integer.MAX_VALUE ? 0 : current+1;
        }while(!atomicInteger.compareAndSet(current,next));
        System.out.println("*****next: "+ next);
        return next;
    }

    //传入某个微服务的所有实例，返回调用的具体实例
    @Override
    public ServiceInstance getIndex(List<ServiceInstance> services) {
        int index = getAndIncrement() % services.size();
        return services.get(index);
    }
}
