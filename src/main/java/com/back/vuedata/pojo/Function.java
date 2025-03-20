package com.back.vuedata.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Function {
    private int id; // 功能ID
    private String pickupPoint; // 取件地点
    private String location; // 快递存放位置
    private String sendTime; //配送时间
    private String publisherName; //发布者姓名
    private String phoneNumber; // 联系电话
    private String description; // 取件码
    private double amount; // 金额
    private String remark; // 备注
    private String publisherId; // 发布者学号
    private String acceptorId; // 接单者学号
    private Timestamp createdAt; // 创建时间
    private Timestamp updatedAt; // 更新时间
}

