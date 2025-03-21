package com.back.vuedata.pojo;

import lombok.Data;

@Data
public class Rotation {
    private Integer id;
    private String theme; //轮播图主题
    private String rotationUrl; //轮播图url
}
