package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
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

    //根据学号和状态查询订单(我的订单）
    @Select("SELECT * FROM orders WHERE publisher_id = #{publisherId} AND status = #{status} ORDER BY created_at DESC")
    List<Orders> findOrdersByPublisherIdAndStatus(@Param("publisherId") String publisherId, @Param("status") String status);

    //根据接单者学号和状态查询订单（我的接单）
    @Select("SELECT * FROM orders WHERE acceptor_id = #{acceptorId} AND status = #{status} ORDER BY created_at DESC")
    List<Orders> findOrdersByReceiverIdAndStatus(@Param("acceptorId") String receiverId, @Param("status") String status);

    // 接单
    @Update("UPDATE orders SET acceptor_id = #{acceptorId}, acceptor_at = #{acceptorAt}, status = '2' WHERE id = #{orderId}")
    void acceptOrder(@Param("orderId") Integer orderId, @Param("acceptorId") String acceptorId, @Param("acceptorAt") Timestamp acceptorAt);

    // 根据订单ID更新订单状态为3并更新时间
    @Update("UPDATE orders SET status = '3', acceptor_at = #{acceptorAt} WHERE id = #{orderId}")
    void updateOrderStatusTo3(@Param("orderId") Integer orderId, @Param("acceptorAt") Timestamp acceptorAt);

    // 取消订单
    @Update("UPDATE orders SET acceptor_id = NULL, acceptor_at = NULL, status = '1' WHERE id = #{orderId}")
    void cancelOrder(@Param("orderId") Integer orderId);

}
