package com.back.vuedata.pojo;

import lombok.Data;
import java.sql.Timestamp;
@Data
public class CollectImages {
    private Integer id; // 自增id
    private String studentId; // 学号
    private String collectUrl; // 收款码
    private Timestamp createdAt; // 创建时间
}
