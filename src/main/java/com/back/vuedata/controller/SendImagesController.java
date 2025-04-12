package com.back.vuedata.controller;

import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.SendImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/send")
public class SendImagesController {
    @Autowired
    private SendImagesService sendImagesService;

    @PostMapping("/upload")
    public Result<String> uploadImage(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = sendImagesService.uploadImage(orderId, file);
            return Result.success(imageUrl);
        } catch (IOException e) {
            return Result.error("上传失败: " + e.getMessage());
        }
    }

    @GetMapping("/latest-url")
    public Result<String> findLatestSendUrlByOrderId(@RequestParam("orderId") Integer orderId) {
        try {
            String sendUrl = sendImagesService.findLatestSendUrlByOrderId(orderId);
            if (sendUrl == null) {
                return Result.success("未找到该订单的最新 send_url");
            }
            return Result.success(sendUrl);
        } catch (Exception e) {
            return Result.error("查询失败: " + e.getMessage());
        }
    }
}