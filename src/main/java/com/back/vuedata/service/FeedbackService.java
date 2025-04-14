package com.back.vuedata.service;

import com.back.vuedata.pojo.Feedback;
import java.util.List;

public interface FeedbackService {

    // 根据用户名查找反馈信息
    List<Feedback> getFeedbacksByStudentId(String studentId);

    // 发布反馈
    void publishFeedback(Feedback feedback);

    int updateFeedbackStatusById(int id, int status);
} 