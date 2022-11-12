package com.yxz.springcloud.controller;

import com.yxz.springcloud.domain.CommonResult;
import com.yxz.springcloud.domain.Order;
import com.yxz.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxiaozhuo
 * @date 2022/11/12
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功!");
    }
    @GetMapping("/order/test/{id}")
    public CommonResult get(@PathVariable("id") Integer id) {
        Order order = orderService.get(id);
        return new CommonResult(200, "订单创建成功!",order);
    }
}
