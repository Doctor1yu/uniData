package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

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

    //根据学号和状态查询订单
    @Select("SELECT * FROM orders WHERE publisher_id = #{publisherId} AND status = #{status} ORDER BY created_at DESC")
    List<Orders> findOrdersByPublisherIdAndStatus(@Param("publisherId") String publisherId, @Param("status") String status);
}
