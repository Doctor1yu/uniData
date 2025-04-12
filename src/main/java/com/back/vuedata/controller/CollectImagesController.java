package com.back.vuedata.controller;

import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.CollectImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/collect")
public class CollectImagesController {
    @Autowired
    private CollectImagesService collectImagesService;

    @PostMapping("/upload")
    public Result<String> uploadCollectImage(
            @RequestParam("studentId") String studentId,
            @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = collectImagesService.uploadCollectImage(studentId, file);
            return Result.success(imageUrl);
        } catch (IOException e) {
            return Result.error("上传失败: " + e.getMessage());
        }
    }

    // 新增：根据 studentId 获取最新的 collect_url
    @GetMapping("/latest-url")
    public Result<String> findLatestCollectUrlByStudentId(@RequestParam("studentId") String studentId) {
        try {
            String collectUrl = collectImagesService.findLatestCollectUrlByStudentId(studentId);
            if (collectUrl == null) {
                return Result.success("未找到该学生的 collect_url");
            }
            return Result.success(collectUrl);
        } catch (Exception e) {
            return Result.error("查询失败: " + e.getMessage());
        }
    }
}