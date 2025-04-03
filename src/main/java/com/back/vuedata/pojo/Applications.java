package com.back.vuedata.pojo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Applications {
    private int id; //自增id
    private String studentId; //学号
    private String applyReason; //申请理由
    private String status; //申请状态（1:已提交，2:未提交，3:通过，4:拒绝）默认：1
    private Timestamp appliedAt; //申请时间
    private String reviewerName; //处理人
    private Timestamp reviewedAt; //审核时间
    private String remark; //审核备注
}
