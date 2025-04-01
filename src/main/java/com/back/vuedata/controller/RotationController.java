package com.back.vuedata.controller;

import com.back.vuedata.pojo.Result;
import com.back.vuedata.pojo.Rotation;
import com.back.vuedata.service.RotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotation")
public class RotationController {

    @Autowired
    private RotationService rotationService;

    @GetMapping("/show")
    public Result getAllRotations() {
        List<Rotation> rotations = rotationService.getAllRotations();
        return Result.success(rotations);
    }
}
