package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.CollectImagesMapper;
import com.back.vuedata.pojo.CollectImages;
import com.back.vuedata.service.CollectImagesService;
import com.back.vuedata.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CollectImagesServiceImpl implements CollectImagesService {
    @Autowired
    private CollectImagesMapper collectImagesMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    public String findLatestCollectUrlByStudentId(String studentId) {
        CollectImages collectImages = collectImagesMapper.findLatestByStudentId(studentId);
        System.out.println(collectImages);
        if (collectImages == null) {
            return null;
        }
        return collectImages.getCollectUrl();
    }

    @Override
    public String uploadCollectImage(String studentId, MultipartFile file) throws IOException {
        // 调用 ImageUtil 上传图片
        String imageUrl = imageUtil.uploadImage(file, "uni/collect_url/");

        // 创建 CollectImages 对象并设置属性
        CollectImages image = new CollectImages();
        image.setStudentId(studentId);
        image.setCollectUrl(imageUrl);

        // 将图片信息存储到数据库
        collectImagesMapper.insert(image);

        return imageUrl;
    }
}
