package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.ApplicationsMapper;
import com.back.vuedata.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    // 提交申请功能
    @Override
    public void submitApplication(Applications application) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(application.getStudentId()) == null) {
            throw new RuntimeException("学号不存在");
        }

        // 设置申请时间为当前时间
        application.setAppliedAt(Timestamp.valueOf(LocalDateTime.now()));

        // 插入新的申请记录
        applicationsMapper.insertApplication(application);

        // 更新 user 表中的 applicationStatus 为 "1"
        userMapper.updateApplicationStatus(application.getStudentId(), "1");
    }

    // 根据学号查询申请状态功能
    @Override
    public String findStatusByStudentId(String studentId) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(studentId) == null) {
            throw new RuntimeException("学号不存在");
        }

        // 从 user 表中查询 applicationStatus
        String status = userMapper.findApplicationStatusByStudentId(studentId);

        // 如果状态为空，则返回默认值 "2"
        return status != null ? status : "2";
    }

    // 重置申请状态功能
    @Override
    public void resetStatus(String studentId) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(studentId) == null) {
            throw new RuntimeException("学号不存在");
        }

        // 只更新 user 表中的 applicationStatus 为 "2"
        userMapper.updateStatusByStudentId(studentId);
    }
}
