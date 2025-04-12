package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.SendImagesMapper;
import com.back.vuedata.pojo.SendImages;
import com.back.vuedata.service.SendImagesService;
import com.back.vuedata.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SendImagesServiceImpl implements SendImagesService {
    @Autowired
    private SendImagesMapper sendImagesMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    public String uploadImage(Integer orderId, MultipartFile file) throws IOException {
    // 调用 ImageUtil 上传图片
    String imageUrl = imageUtil.uploadImage(file, "uni/send_url/");

    // 创建 Images 对象并设置属性
    SendImages image = new SendImages();
    image.setOrderId(orderId);
    image.setSendUrl(imageUrl);

    // 将图片信息存储到数据库
    sendImagesMapper.insert(image); // 需要在 ImagesMapper 中定义 insert 方法

    return imageUrl; // 返回图片的 URL
}
}