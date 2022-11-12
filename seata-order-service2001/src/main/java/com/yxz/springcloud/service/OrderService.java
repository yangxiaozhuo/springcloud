package com.yxz.springcloud.service;

import com.yxz.springcloud.domain.Order;

/**
 * @author yangxiaozhuo
 * @date 2022/11/12
 */
public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);

    Order get(Integer id);
}
