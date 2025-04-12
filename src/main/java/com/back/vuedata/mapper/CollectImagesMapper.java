package com.back.vuedata.mapper;

import com.back.vuedata.pojo.CollectImages;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CollectImagesMapper {
    @Insert("INSERT INTO collect_images (student_id, collect_url) VALUES (#{studentId}, #{collectImage})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(CollectImages image);
}
