package com.back.vuedata.service;

import com.back.vuedata.pojo.Orders;

public interface OrderService {

    //添加订单
    void publishOrder(Orders orders);
} 