package com.wanghq.springcloud.alibaba.service;

import com.wanghq.springcloud.alibaba.domain.Order;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-14 22:54
 */
public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
