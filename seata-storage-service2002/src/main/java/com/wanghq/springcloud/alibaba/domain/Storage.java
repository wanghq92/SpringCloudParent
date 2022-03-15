package com.wanghq.springcloud.alibaba.domain;

import lombok.Data;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-17 22:36
 */
@Data
public class Storage {
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
