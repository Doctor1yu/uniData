package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    //发布订单
    @Insert("INSERT INTO orders (pickup_point, location, send_at, publisher_name, phone_number, description, amount, remark,status, publisher_id, created_at) " +
            "VALUES (#{pickupPoint}, #{location}, #{sendAt}, #{publisherName}, #{phoneNumber}, #{description}, #{amount}, #{remark},#{status}, #{publisherId}, #{createdAt})")
    void insertOrder(Orders orders);

    //获取所有订单，按创建时间从最新到旧排序
    @Select("SELECT * FROM orders WHERE status='1' ORDER BY created_at DESC")
    List<Orders> getAllOrders();
}
