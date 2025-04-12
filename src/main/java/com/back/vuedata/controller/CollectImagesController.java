package com.back.vuedata.controller;

import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.CollectImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}