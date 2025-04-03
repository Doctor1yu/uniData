package com.back.vuedata.service;

import com.back.vuedata.pojo.Applications;

public interface ApplicationsService {
    void submitApplication(Applications application);
    String findStatusByStudentId(String studentId);
    void resetStatus(String studentId);
    Applications findLatestApplicationByStudentId(String studentId);
}
