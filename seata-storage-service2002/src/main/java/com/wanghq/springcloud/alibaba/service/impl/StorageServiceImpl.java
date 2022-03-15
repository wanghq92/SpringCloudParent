package com.wanghq.springcloud.alibaba.service.impl;

import com.wanghq.springcloud.alibaba.dao.StorageDao;
import com.wanghq.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022-01-17 22:48
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------>storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------>storage-service中扣减库存结束");
    }
}
