package com.wanghq.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wanghq.springcloud.entities.CommonResult;

/**
 * @author whq
 * @version 1.0.0
 * @Description 自定义限流处理类
 * @createTime 2022-01-11 15:13
 */
public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息2......CustomerBlockHandler2");
    }

}
