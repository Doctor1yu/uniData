package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.FeedbackMapper;
import com.back.vuedata.pojo.Feedback;
import com.back.vuedata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackMapper.findAll();
    }
} 