package com.back.vuedata.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Orders {
    private int id; // 功能ID
    private String pickupPoint; // 取件地点
    private String location; // 快递存放位置
    private String sendAt; // 配送时间（与数据库字段send_at匹配）
    private String publisherName; // 发布者姓名
    private String phoneNumber; // 联系电话
    private String description; // 取件码
    private double amount; // 金额
    private String remark; // 备注
    private String status; // 订单状态（1代表未接单、2代表进行中、3代表已完成
    private String publisherId; // 发布者学号
    private String acceptorId; // 接单者学号（不插入）
    private Timestamp createdAt; // 创建时间
    private Timestamp acceptorAt; // 接单时间（不插入）
}

