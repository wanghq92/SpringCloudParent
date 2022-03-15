package com.wanghq.springcloud.alibaba.service.impl;

import com.wanghq.springcloud.alibaba.dao.OrderDao;
import com.wanghq.springcloud.alibaba.domain.Order;
import com.wanghq.springcloud.alibaba.service.AccountService;
import com.wanghq.springcloud.alibaba.service.OrderService;
import com.wanghq.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-14 22:55
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    //name的值是任意的
    @Override
//    @GlobalTransactional(name="my_global_transaction",rollbackFor = Exception.class)
    public void create(Order order) {

        /**
         * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
         * 简单说：
         * 下订单->减库存->减余额->改状态
         */
        //1 新建订单
        log.info("-------->开始新建订单");
        orderDao.create(order);
        //2 减库存
        log.info("-------->订单微服务调用库存微服务，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("-------->订单微服务调用库存微服务，做扣减 end");
        //3 减余额
        log.info("-------->订单微服务调用账户微服务，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-------->订单微服务调用账户微服务，做扣减 end");
        //4 改状态
        log.info("-------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("-------->修改订单状态结束");

        log.info("-------->下单结束！");







    }
}
