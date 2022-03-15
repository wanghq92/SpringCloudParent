package com.wanghq.springcloud.alibaba.service;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-17 22:47
 */
public interface StorageService {

    //减库存
    void decrease(Long productId,Integer count);
}
