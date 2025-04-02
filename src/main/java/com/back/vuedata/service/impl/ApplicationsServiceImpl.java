package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.ApplicationsMapper;
import com.back.vuedata.pojo.Applications;
import com.back.vuedata.service.ApplicationsService;
import com.back.vuedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ApplicationsServiceImpl implements ApplicationsService {

    @Autowired
    private ApplicationsMapper applicationsMapper;

    @Autowired
    private UserService userService;

    @Override
    public void submitApplication(Applications application) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(application.getStudentId()) == null) {
            throw new RuntimeException("学号不存在");
        }

        application.setAppliedAt(Timestamp.valueOf(LocalDateTime.now())); // 设置申请时间为当前时间
        applicationsMapper.insertApplication(application);
    }

    @Override
    public String findStatusByStudentId(String studentId) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(studentId) == null) {
            throw new RuntimeException("学号不存在");
        }

        String status = applicationsMapper.findStatusByStudentId(studentId);
        return status != null ? status : "未申请";
    }
}
