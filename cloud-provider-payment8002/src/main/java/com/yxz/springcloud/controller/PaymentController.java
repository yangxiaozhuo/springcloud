package com.yxz.springcloud.controller;

import com.yxz.springcloud.entities.CommonResult;
import com.yxz.springcloud.entities.Payment;
import com.yxz.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping()
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功, serverPort : " + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败, serverPort : " + serverPort, null);
        }
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功, serverPort : " + serverPort, payment);
        } else {
            return new CommonResult(444, "没有ID = " + id + " 的对应记录, serverPort : " + serverPort, null);
        }
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }
}
