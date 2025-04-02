package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.ApplicationsMapper;
import com.back.vuedata.pojo.Applications;
import com.back.vuedata.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ApplicationsServiceImpl implements ApplicationsService {

    @Autowired
    private ApplicationsMapper applicationsMapper;

    @Override
    public void submitApplication(Applications application) {
        application.setAppliedAt(Timestamp.valueOf(LocalDateTime.now())); // 设置申请时间为当前时间
        applicationsMapper.insertApplication(application);
    }
}
