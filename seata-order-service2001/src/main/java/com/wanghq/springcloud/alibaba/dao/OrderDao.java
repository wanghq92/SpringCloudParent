package com.wanghq.springcloud.alibaba.dao;

import com.wanghq.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-14 22:39
 */
@Mapper
public interface OrderDao {
    //创建订单
    void create(Order order);

    //修改订单状态
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
