package com.back.vuedata.controller;

import com.back.vuedata.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
    @Autowired
    private ImagesService imagesService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("file") MultipartFile file){
        try {
            String imageUrl = imagesService.uploadImage(orderId, file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败: " + e.getMessage());
        }
    }
}