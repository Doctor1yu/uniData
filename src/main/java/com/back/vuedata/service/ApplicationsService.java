package com.back.vuedata.service;

import com.back.vuedata.pojo.Applications;

public interface ApplicationsService {
    // 提交申请
    void submitApplication(Applications application);
    // 重置状态
    void resetStatus(String studentId);
    // 查询状态
    String findStatusByStudentId(String studentId);
    // 查询最新申请
    Applications findLatestApplicationByStudentId(String studentId);
}