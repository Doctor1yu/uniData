package com.back.vuedata.service;

import com.back.vuedata.pojo.Orders;

import java.util.List;

public interface OrderService {

    // 发布订单
    void publishOrder(Orders orders);

    // 获取已发布的订单
    List<Orders> getAllOrders();
} 