package com.back.vuedata.controller;

import com.back.vuedata.pojo.Feedback;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // 根据学号查找反馈
    @GetMapping("/feedbacks")
    public Result<List<Feedback>> getFeedbacksByStudentId(@RequestParam String studentId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByStudentId(studentId);
        return Result.success(feedbacks);
    }

    // 发布反馈
    @PostMapping("/issue")
    public Result publishFeedback(Feedback feedback) {
        feedbackService.publishFeedback(feedback);
        return Result.success(feedback);
    }

    @PatchMapping("/update")
    public Result updateFeedbackStatusById(@RequestParam int id) {
        int result = feedbackService.updateFeedbackStatusById(id, 3);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("更新失败，反馈可能不存在");
        }
    }
} 