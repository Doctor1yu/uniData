package com.back.vuedata.service.impl;

import com.back.vuedata.pojo.Rotation;
import com.back.vuedata.mapper.RotationMapper;
import com.back.vuedata.service.RotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationServiceImpl implements RotationService {
    
    @Autowired
    private RotationMapper rotationMapper;

    @Override
    public List<Rotation> getAllRotations() {
        return rotationMapper.selectAll();
    }
}
