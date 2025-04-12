package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Images;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ImagesMapper {

    @Insert("INSERT INTO images (order_id, send_url) VALUES (#{orderId}, #{sendUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Images image);
}