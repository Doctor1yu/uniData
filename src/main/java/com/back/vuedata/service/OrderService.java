package com.back.vuedata.service;

import com.back.vuedata.pojo.Orders;

import java.util.List;

public interface OrderService {

    // 发布订单
    void publishOrder(Orders orders);

    // 获取已发布的订单
    List<Orders> getAllOrders();

    // 根据学号和状态查询订单（我的订单）
    List<Orders> findOrdersByPublisherIdAndStatus(String publisherId, String status);

    // 根据接单者学号和状态查询订单（我的接单）
    List<Orders> findOrdersByReceiverIdAndStatus(String acceptorId, String status);
} 