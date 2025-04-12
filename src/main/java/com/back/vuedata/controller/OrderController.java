package com.back.vuedata.controller;

import com.back.vuedata.pojo.Orders;
import com.back.vuedata.pojo.User;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.OrderService;
import com.back.vuedata.service.UserService;
import com.back.vuedata.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/function")
public class OrderController {

    // 自定义上传路径
    private static final String SEND_URL_PATH = "/uni/send_url";

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageUtil imageUtil;

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

    // 根据学号和状态查询订单（我的订单）
    @GetMapping("/orders/by-status")
    public Result getOrdersByStatus(@RequestParam String publisherId, @RequestParam String status) {
        List<Orders> orders = orderService.findOrdersByPublisherIdAndStatus(publisherId, status);
        enrichOrdersWithUserInfo(orders);
        return Result.success(orders);
    }

    // 根据接单者学号和状态查询订单（我的接单）
    @GetMapping("/orders/received")
    public Result getReceivedOrdersByStatus(@RequestParam String acceptorId, @RequestParam String status) {
        List<Orders> orders = orderService.findOrdersByReceiverIdAndStatus(acceptorId, status);
        enrichOrdersWithUserInfo(orders);
        return Result.success(orders);
    }

    // 接单
    @PatchMapping("/orders/accept")
    public Result acceptOrder(@RequestParam Integer orderId, @RequestParam String acceptorId) {
        orderService.acceptOrder(orderId, acceptorId);
        return Result.success();
    }

    // 根据订单ID更新订单状态为3
    @PatchMapping("/orders/update-status")
    public Result updateOrderStatusTo3(@RequestParam Integer orderId) {
        orderService.updateOrderStatusTo3(orderId);
        return Result.success();
    }

    // 取消订单
    @PatchMapping("/orders/cancel")
    public Result cancelOrder(@RequestParam Integer orderId) {
        orderService.cancelOrder(orderId);
        return Result.success();
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