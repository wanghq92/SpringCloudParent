package com.wanghq.springcloud.dao;

import com.wanghq.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 15:33
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id")Long id);
}
