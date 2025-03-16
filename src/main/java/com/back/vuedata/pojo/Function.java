package com.back.vuedata.pojo;

import java.sql.Timestamp;

public class Function {
    private int id; // 功能ID
    private int fun; // 功能类型 1、取快递 2、寄快递 3、万能任务
    private String pickupPoint; // 取件点
    private String receivePoint; // 收件点
    private String location; // 取快递位置
    private String description; // 描述
    private String phoneNumber; // 联系电话
    private double amount; // 金额
    private String remark; // 备注
    private String publisherId; // 发布者ID
    private String publisherNickname; // 发布者昵称
    private String acceptorId; // 接受者ID
    private Timestamp createdAt; // 创建时间
    private Timestamp updatedAt; // 更新时间

    public Function() {
    }

    public Function(int id, int fun, String pickupPoint, String receivePoint, String location, String description, String phoneNumber, double amount, String remark, String publisherId, String publisherNickname, String acceptorId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.fun = fun;
        this.pickupPoint = pickupPoint;
        this.receivePoint = receivePoint;
        this.location = location;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.remark = remark;
        this.publisherId = publisherId;
        this.publisherNickname = publisherNickname;
        this.acceptorId = acceptorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", fun=" + fun +
                ", pickupPoint='" + pickupPoint + '\'' +
                ", receivePoint='" + receivePoint + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                ", publisherId=" + publisherId +
                ", publisherNickname='" + publisherNickname + '\'' +
                ", acceptorId=" + acceptorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFun() {
        return fun;
    }

    public void setFun(int fun) {
        this.fun = fun;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getReceivePoint() {
        return receivePoint;
    }

    public void setReceivePoint(String receivePoint) {
        this.receivePoint = receivePoint;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherNickname() {
        return publisherNickname;
    }

    public void setPublisherNickname(String publisherNickname) {
        this.publisherNickname = publisherNickname;
    }

    public String getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(String acceptorId) {
        this.acceptorId = acceptorId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

