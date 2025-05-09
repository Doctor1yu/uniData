package com.back.vuedata.mapper;

import com.back.vuedata.pojo.SendImages;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SendImagesMapper {

    @Insert("INSERT INTO send_images (order_id, send_url, create_at) VALUES (#{orderId}, #{sendUrl}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SendImages image);

    @Select("SELECT * FROM send_images WHERE order_id = #{orderId} ORDER BY id DESC LIMIT 1")
    SendImages findLatestByOrderId(Integer orderId);
}