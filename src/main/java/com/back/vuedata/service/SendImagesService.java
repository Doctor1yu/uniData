package com.back.vuedata.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SendImagesService {

    String uploadImage(Integer orderId, MultipartFile file) throws IOException;
}