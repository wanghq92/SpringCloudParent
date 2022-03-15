package com.wanghq.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-16 15:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
      private Integer code;
      private String message;
      private T data;

      public CommonResult(Integer code,String message){
            this(code,message,null); //此处报错的话 下载lombak 插件
      }
}
