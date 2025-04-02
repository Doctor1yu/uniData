package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.OrderMapper;
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

    @Override
    public List<Orders> findOrdersByPublisherIdAndStatus(String publisherId, String status) {
        return orderMapper.findOrdersByPublisherIdAndStatus(publisherId, status);
    }

    @Override
    public List<Orders> findOrdersByReceiverIdAndStatus(String acceptorId, String status) {
        return orderMapper.findOrdersByReceiverIdAndStatus(acceptorId, status);
    }

    @Override
    public void acceptOrder(Integer orderId, String acceptorId) {
        Timestamp acceptorAt = Timestamp.valueOf(LocalDateTime.now());
        orderMapper.acceptOrder(orderId, acceptorId, acceptorAt);
    }
} 