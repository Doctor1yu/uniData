package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.OrderMapper;
import com.back.vuedata.mapper.SendImagesMapper;
import com.back.vuedata.pojo.Orders;
import com.back.vuedata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SendImagesMapper sendImagesMapper;

    //发布订单
    @Override
    public void publishOrder(Orders orders) {
        orders.setAcceptorId(null);
        orders.setAcceptorAt(null);
        orders.setStatus("1");
        orders.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        orderMapper.insertOrder(orders);
    }

    //获取已发布的订单
    @Override
    public List<Orders> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    //根据学号和状态查询订单（我的订单）
    @Override
    public List<Orders> findOrdersByPublisherIdAndStatus(String publisherId, String status) {
        return orderMapper.findOrdersByPublisherIdAndStatus(publisherId, status);
    }

    //根据接单者学号和状态查询订单（我的接单）
    @Override
    public List<Orders> findOrdersByReceiverIdAndStatus(String acceptorId, String status) {
        return orderMapper.findOrdersByReceiverIdAndStatus(acceptorId, status);
    }

    //接单
    @Override
    public void acceptOrder(Integer orderId, String acceptorId) {
        Timestamp acceptorAt = Timestamp.valueOf(LocalDateTime.now());
        orderMapper.acceptOrder(orderId, acceptorId, acceptorAt);
    }

    //根据订单ID更新订单状态为3并设置send_url
    @Override
    public void updateOrderStatusTo3(Integer orderId, String sendUrl) {
        Timestamp acceptorAt = Timestamp.valueOf(LocalDateTime.now());
        orderMapper.updateOrderStatusTo3(orderId, acceptorAt, sendUrl);
    }

    //接单者取消订单
    @Override
    public void acceptorCancelOrder(Integer orderId) {
        orderMapper.acceptorCancelOrder(orderId);
    }

    //发布者取消订单
    @Override
    public void publisherCancelOrder(Integer orderId) {
        orderMapper.publisherCancelOrder(orderId);
    }
} 