package com.back.vuedata.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Feedback {
    private int id; // 反馈ID
    private String subject; // 反馈主题
    private String content; // 反馈内容
    private String phoneNumber; // 联系电话
    private int status; // 处理状态（1代表未解决、2代表已解决）
    private String studentId; // 发布者学号
    private String answer; // 回复
    private String answerName; // 处理人
    private Timestamp createdAt; // 创建时间
    private Timestamp updatedAt; // 更新时间
}