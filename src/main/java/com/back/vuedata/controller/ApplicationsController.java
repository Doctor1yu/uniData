package com.back.vuedata.controller;

import com.back.vuedata.pojo.Applications;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.ApplicationsService;
import com.back.vuedata.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    // 自定义上传路径
    private static final String COLLECT_URL_PATH = "/uni/collect_url";

    @Autowired
    private ApplicationsService applicationsService;

    @Autowired
    private ImageUtil imageUtil;

    // 提交学生申请
    @PostMapping("/submit")
    public Result submitApplication( @RequestParam String studentId,  @RequestParam String applyReason,  @RequestParam(required = false) MultipartFile collectImage) {
        try {
            Applications application = new Applications();
            application.setStudentId(studentId);
            application.setApplyReason(applyReason);
            
            // 处理收款码图片上传
            if (collectImage != null && !collectImage.isEmpty()) {
                String collectUrl = imageUtil.uploadImage(collectImage, COLLECT_URL_PATH);
                application.setCollectUrl(collectUrl);
            }
            
            applicationsService.submitApplication(application);
            return Result.success("申请提交成功");
        } catch (RuntimeException | IOException e) {
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

    // 根据学生ID查询最新申请记录
    @GetMapping("/latest")
    public Result findLatestApplicationByStudentId(@RequestParam String studentId) {
        try {
            Applications application = applicationsService.findLatestApplicationByStudentId(studentId);
            if (application == null) {
                return Result.success("该学生暂无申请记录");
            }
            return Result.success(application);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
}
