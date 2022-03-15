package com.wanghq.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-08 13:00
 */
@RestController
@Slf4j
public class FlowLimitController
{

    @GetMapping("/testA")
    public String testA()
    {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName()+"------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info("------testB");
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC()
    {
        log.info("testC 测试异常比例");
        int age = 10/0;
        return "------testC";
    }
    @GetMapping("/testD")
    public String testD()
    {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        return "------testD";
    }
    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常比例");
        int age = 10/0;
        return "------testE 测试异常比例";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value="testHotKey",blockHandler="dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value="p1",required=false)String p1,
                             @RequestParam(value="p2",required=false) String p2){
//        int i = 10/0;
        return "----testHotKey,  p1=" + p1+"\t"+ "p2 = "+p2;
    }
    public String dealHandler_testHotKey(String p1, String p2, BlockException exception){
        return "---------dealHandler_testHotKey";
    }

}
