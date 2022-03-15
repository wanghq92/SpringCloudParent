package com.wanghq.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-17 22:37
 */
@Mapper
public interface StorageDao {
    //减库存
    void decrease(@Param("productId")Long productId,@Param("count") Integer count);
}
