package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO orders (pickup_point, location, send_at, publisher_name, phone_number, description, amount, remark, publisher_id, created_at) " +
            "VALUES (#{pickupPoint}, #{location}, #{sendAt}, #{publisherName}, #{phoneNumber}, #{description}, #{amount}, #{remark}, #{publisherId}, #{createdAt})")
    void insertOrder(Orders orders);
}
