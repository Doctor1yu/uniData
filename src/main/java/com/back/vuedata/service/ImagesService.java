package com.back.vuedata.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagesService {

    String uploadImage(Integer orderId, MultipartFile file) throws IOException;
}