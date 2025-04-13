package com.back.vuedata.controller;

import com.back.vuedata.pojo.Orders;
import com.back.vuedata.pojo.User;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.OrderService;
import com.back.vuedata.service.SendImagesService;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private SendImagesService sendImagesService;

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
        // 获取最新的 send_url
        String sendUrl = sendImagesService.findLatestSendUrlByOrderId(orderId);

        // 更新订单状态并设置 send_url
        orderService.updateOrderStatusTo3(orderId, sendUrl);
        return Result.success();
    }

    // 接单者取消订单
    @PatchMapping("/orders/acceptor-cancel")
    public Result acceptorCancelOrder(@RequestParam Integer orderId) {
        orderService.acceptorCancelOrder(orderId);
        return Result.success();
    }

    // 发布者取消订单
    @DeleteMapping("/orders/publisher-cancel")
    public Result publisherCancelOrder(@RequestParam Integer orderId) {
        try {
            orderService.publisherCancelOrder(orderId);
            return Result.success("订单删除成功");
        } catch (Exception e) {
            return Result.error("订单删除失败: " + e.getMessage());
        }
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

    // 获取接单者收款码
    @GetMapping("/orders/acceptor-collect-url")
    public Result getAcceptorCollectUrl(@RequestParam String acceptorId) {
        // 根据 acceptorId 查找用户信息
        User user = userService.findUserByStudentId(acceptorId);
        
        // 检查用户是否存在
        if (user == null) {
            return Result.error("未找到接单者的用户信息");
        }
        
        // 获取并返回 collectUrl
        String collectUrl = user.getCollectUrl();
        if (collectUrl == null) {
            return Result.error("未找到接单者的收款码");
        }
        
        return Result.success(collectUrl);
    }
}
