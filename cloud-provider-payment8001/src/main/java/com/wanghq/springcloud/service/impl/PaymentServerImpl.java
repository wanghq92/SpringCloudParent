package com.wanghq.springcloud.service.impl;

import com.wanghq.springcloud.dao.PaymentDao;
import com.wanghq.springcloud.entities.Payment;
import com.wanghq.springcloud.service.PaymentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 15:50
 */
@Service
public class PaymentServerImpl implements PaymentServer {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
