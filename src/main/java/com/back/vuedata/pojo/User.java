package com.back.vuedata.pojo;

import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

public class User {
    private int id; // 用户ID
    private String studentId; // 学号
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$", message = "密码必须是8-12位字符，且包含大小写字母和数字")
    private String password; // 密码
    private String confirmPassword; // 确认密码
    private String phoneNumber; // 手机号
    private String nickName; // 昵称
    private String avatarUrl; // 头像地址（可选）
    private Timestamp createdAt; // 创建时间
    private Timestamp updatedAt; // 更新时间

    public User() {
    }

    public User(int id, String studentId, String password, String confirmPassword, String phoneNumber, String nickName,
                String avatarUrl, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentId='" + studentId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}