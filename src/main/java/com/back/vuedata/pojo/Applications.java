package com.back.vuedata.pojo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Applications {
    private int id; //自增id
    private String studentId; //学号
    private String applyReason; //申请理由
    private String status; //状态（1代表待审核，2代表通过，3代表拒绝）
    private Timestamp appliedAt; //申请时间
    private String reviewerId; //处理人id
    private Timestamp reviewedAt; //审核时间
    private String remark; //审核备注
}
