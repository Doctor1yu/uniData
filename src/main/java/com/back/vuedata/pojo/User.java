package com.back.vuedata.pojo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class User {
    private int id; // 用户ID
    private String studentId; // 学号
    private String password; // 密码
    private String confirmPassword; // 确认密码
    private String phoneNumber; // 手机号
    private String nickName; // 昵称
    private String avatarUrl; // 头像地址（可选）
    private String role; //身份（1代表普通用户，2代表接单员）
    private String status; //状态（1代表正常，2代表不正常）
    private Timestamp createdAt; // 创建时间
    private Timestamp updatedAt; // 更新时间
    
    private String applicationStatus; // 申请状态
}