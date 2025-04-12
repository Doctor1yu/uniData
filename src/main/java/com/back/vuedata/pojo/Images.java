package com.back.vuedata.pojo;

import lombok.Data;

@Data
public class Images {
    private Integer id; // 自增id
    private Integer orderId; // 订单id
    private String sendUrl; // 收货地点照片
}
