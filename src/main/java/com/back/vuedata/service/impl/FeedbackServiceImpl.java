package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.FeedbackMapper;
import com.back.vuedata.pojo.Feedback;
import com.back.vuedata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> getFeedbacksByStudentId(String studentId) {
        return feedbackMapper.findByStudentId(studentId);
    }

    @Override
    public void publishFeedback(Feedback feedback) {
        // 设置默认状态为未解决
        feedback.setStatus(1);
        // 设置创建时间和更新时间
        feedback.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        feedback.setUpdatedAt(null);
        // 插入反馈
        feedbackMapper.insertFeedback(feedback);
    }

    @Override
    public int updateFeedbackStatusById(int id, int status) {
        return feedbackMapper.updateFeedbackStatusById(id, status);
    }
} 