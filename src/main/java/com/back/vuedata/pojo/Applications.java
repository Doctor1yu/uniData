package com.back.vuedata.pojo;

public class Applications {
    private int id; //自增id
    private String StudentId; //学号
    private String ApplyReason; //申请理由
    private String status; //状态（1代表待审核，2代表通过，3代表拒绝）
    private String appliedAt; //申请时间
    private String ReviewerId; //处理人id
    private String ReviewedAt; //审核时间
    private String remark; //审核备注
}
