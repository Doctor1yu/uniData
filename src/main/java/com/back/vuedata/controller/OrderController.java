package com.back.vuedata.controller;

import com.back.vuedata.pojo.Orders;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/function")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/publish")
    public Result publishOrder(Orders orders) {
        orderService.publishOrder(orders);

        return Result.success(orders);
    }
}