package com.back.vuedata.controller;

import com.back.vuedata.pojo.Applications;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    @Autowired
    private ApplicationsService applicationsService;

    // 提交学生申请
    @PostMapping("/submit")
    public Result submitApplication(@RequestParam String studentId, @RequestParam String applyReason) {
        try {
            Applications application = new Applications();
            application.setStudentId(studentId);
            application.setApplyReason(applyReason);
            applicationsService.submitApplication(application);
            return Result.success("申请提交成功");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 根据学生ID重置申请状态（重置的是user表中的application_status）
    @PatchMapping("/reset-status")
    public Result resetStatusByStudentId(@RequestParam String studentId) {
        try {
            applicationsService.resetStatus(studentId);
            return Result.success("状态重置成功");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
}
