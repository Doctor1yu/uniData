package com.back.vuedata.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface CollectImagesService {
    String uploadCollectImage(String studentId, MultipartFile file) throws IOException;
}
