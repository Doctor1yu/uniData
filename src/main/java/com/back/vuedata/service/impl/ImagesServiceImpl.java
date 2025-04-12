package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.ImagesMapper;
import com.back.vuedata.pojo.Images;
import com.back.vuedata.service.ImagesService;
import com.back.vuedata.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImagesMapper imagesMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    public String uploadImage(Integer orderId, MultipartFile file) throws IOException {
    // 调用 ImageUtil 上传图片
    String imageUrl = imageUtil.uploadImage(file, "images/");

    // 创建 Images 对象并设置属性
    Images image = new Images();
    image.setOrderId(orderId);
    image.setSendUrl(imageUrl);

    // 将图片信息存储到数据库
    imagesMapper.insert(image); // 需要在 ImagesMapper 中定义 insert 方法

    return imageUrl; // 返回图片的 URL
}
}