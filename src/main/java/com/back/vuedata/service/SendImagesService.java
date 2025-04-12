package com.back.vuedata.service;

import com.back.vuedata.pojo.SendImages;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SendImagesService {

    String uploadImage(Integer orderId, MultipartFile file) throws IOException;
    String findLatestSendUrlByOrderId(Integer orderId);
}