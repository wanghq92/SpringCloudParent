package com.wanghq.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 15:26
 */
@Data   //lombak 提供set get方法
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //空参构造器
public class Payment implements Serializable {
    private Long id;
    private String  serial;
}
