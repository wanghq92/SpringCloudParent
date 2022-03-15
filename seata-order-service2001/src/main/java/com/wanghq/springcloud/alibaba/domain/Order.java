package com.wanghq.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-14 22:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;//订单主键

    private Long userId;//用户id

    private Long productId;//商品id

    private Integer count;//购买数量

    private BigDecimal money;//金额

    /**
     * 订单状态：0：创建中；1：已完结
     */
    private Integer status;

}
