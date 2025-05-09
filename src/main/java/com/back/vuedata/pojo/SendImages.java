package com.back.vuedata.pojo;

import lombok.Data;
import java.sql.Timestamp;
@Data
public class SendImages {
    private Integer id; // 自增id
    private Integer orderId; // 订单id
    private String sendUrl; // 收货地点照片
    private Timestamp createdAt; // 创建时间
}
