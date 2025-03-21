package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.OrderMapper;
import com.back.vuedata.pojo.Orders;
import com.back.vuedata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void publishOrder(Orders orders) {
        orders.setAcceptorId(null);
        orders.setAcceptorAt(null);
        orders.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        orderMapper.insertOrder(orders);
    }
} 