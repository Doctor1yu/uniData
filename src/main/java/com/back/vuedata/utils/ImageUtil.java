package com.back.vuedata.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class ImageUtil {
    // 七牛云对象存储的关键配置
    private static final String ACCESS_KEY = "gdZkv3g6q5n-xH_Xr28Gsq_QTfefkJtc_GSiHH9R";
    private static final String SECRET_KEY = "8DPElj36n9s1LPzxARynWOyUfcYO6F4ysB28Q9RS";
    private static final String BUCKET_NAME = "hwo-bishe";
    private static final String DOMAIN = "image.starhub666.cn";

    // 七牛云上传的核心组件
    private final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private final Configuration cfg = new Configuration();
    private final UploadManager uploadManager = new UploadManager(cfg);

    // 图片上传方法，接收自定义路径
    public String uploadImage(MultipartFile file, String storagePath) throws IOException {
        // 文件非空检查
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        // 文件大小限制（例如：5MB）
        long maxSize = 5 * 1024 * 1024; // 5MB
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("文件大小不能超过5MB");
        }

        // 文件类型检查
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("只允许上传图片文件");
        }

        // 生成唯一文件名（可选）
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".jpg";

        // 去掉路径开头的斜杠，七牛云不需要以斜杠开头
        String path = storagePath;
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        // 确保路径末尾有斜杠
        if (!path.endsWith("/")) {
            path = path + "/";
        }

        // 使用存储路径和唯一文件名
        String uniqueFileName = path + UUID.randomUUID().toString() + fileExtension;

        try {
            // 生成上传凭证
            String upToken = auth.uploadToken(BUCKET_NAME);

            // 执行文件上传
            Response response = uploadManager.put(
                    file.getBytes(),           // 文件字节数组
                    uniqueFileName,            // 使用带路径的唯一文件名
                    upToken                    // 上传凭证
            );

            // 解析上传响应
            DefaultPutRet putRet = new Gson().fromJson(
                    response.bodyString(),
                    DefaultPutRet.class
            );

            // 返回完整的图片访问URL（包含http://前缀）
            return "http://" + DOMAIN + "/" + putRet.key;
        } catch (QiniuException ex) {
            // 错误处理
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // 忽略二次异常
            }
            throw new IOException("上传失败: " + ex.getMessage());
        }
    }
}

