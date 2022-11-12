package com.yxz.springcloud.service;

/**
 * @author yangxiaozhuo
 * @date 2022/11/12
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
