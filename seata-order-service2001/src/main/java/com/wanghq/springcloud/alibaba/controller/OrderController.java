package com.wanghq.springcloud.alibaba.controller;

import com.wanghq.springcloud.alibaba.domain.CommonResult;
import com.wanghq.springcloud.alibaba.domain.Order;
import com.wanghq.springcloud.alibaba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-17 16:20
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功!");
    }

}
