package com.back.vuedata.controller;

import com.back.vuedata.pojo.Orders;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/function")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 发布订单
    @PostMapping("/publish")
    public Result publishOrder(@RequestBody Orders orders) {
        orderService.publishOrder(orders);
        return Result.success(orders);
    }

    // 获取所有订单
    @GetMapping("/orders")
    public Result getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        return Result.success(orders);
    }
}