package com.back.vuedata.controller;

import com.back.vuedata.pojo.Orders;
import com.back.vuedata.pojo.User;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.OrderService;
import com.back.vuedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/function")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // 发布订单
    @PostMapping("/publish")
    public Result publishOrder(Orders orders) {
        orderService.publishOrder(orders);
        return Result.success(orders);
    }

    // 获取所有订单
    @GetMapping("/orders")
    public Result getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        enrichOrdersWithUserInfo(orders);
        return Result.success(orders);
    }

    // 根据学号和状态查询订单
    @GetMapping("/orders/by-status")
    public Result getOrdersByStatus(@RequestParam String publisherId, @RequestParam String status) {
        List<Orders> orders = orderService.findOrdersByPublisherIdAndStatus(publisherId, status);
        enrichOrdersWithUserInfo(orders);
        return Result.success(orders);
    }

    // 辅助方法：为订单添加发布者的 avatarUrl 和 nickName
    private void enrichOrdersWithUserInfo(List<Orders> orders) {
        for (Orders order : orders) {
            String publisherId = order.getPublisherId();
            if (publisherId != null) {
                User user = userService.findUserByStudentId(publisherId);
                if (user != null) {
                    order.setPublisherAvatarUrl(user.getAvatarUrl());
                    order.setPublisherNickName(user.getNickName());
                }
            }
        }
    }
}