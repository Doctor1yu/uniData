package com.back.vuedata.service;

import com.back.vuedata.pojo.Applications;

public interface ApplicationsService {
    void submitApplication(Applications application);
    void resetStatus(String studentId);
    String findStatusByStudentId(String studentId);
    Applications findLatestApplicationByStudentId(String studentId);
}