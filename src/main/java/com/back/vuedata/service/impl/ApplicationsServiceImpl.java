package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.ApplicationsMapper;
import com.back.vuedata.mapper.CollectImagesMapper;
import com.back.vuedata.mapper.UserMapper;
import com.back.vuedata.pojo.Applications;
import com.back.vuedata.pojo.CollectImages;
import com.back.vuedata.service.ApplicationsService;
import com.back.vuedata.service.CollectImagesService;
import com.back.vuedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationsServiceImpl implements ApplicationsService {

    @Autowired
    private ApplicationsMapper applicationsMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CollectImagesService collectImagesService;

    // 提交申请
    @Override
    public void submitApplication(Applications application) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(application.getStudentId()) == null) {
            throw new RuntimeException("学号不存在");
        }

        // 查找最新的 collectUrl
        String collectUrl = collectImagesService.findLatestCollectUrlByStudentId(application.getStudentId());
        if (collectUrl != null) {
            application.setCollectUrl(collectUrl);
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

    // 查询最新申请
    @Override
    public Applications findLatestApplicationByStudentId(String studentId) {
        // 检查学号是否存在
        if (userService.findUserByStudentId(studentId) == null) {
            throw new RuntimeException("学号不存在");
        }

        // 查询该学生的最新申请记录
        List<Applications> applications = applicationsMapper.findApplicationsByStudentId(studentId);
        if (applications.isEmpty()) {
            return null; // 如果没有申请记录，返回 null
        }

        // 返回最新的申请记录（按申请时间降序排序后的第一条）
        return applications.stream()
                .sorted((a1, a2) -> a2.getAppliedAt().compareTo(a1.getAppliedAt()))
                .findFirst()
                .orElse(null);
    }
}
