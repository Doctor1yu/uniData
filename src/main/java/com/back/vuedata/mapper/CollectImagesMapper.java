package com.back.vuedata.mapper;

import com.back.vuedata.pojo.CollectImages;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectImagesMapper {
    @Insert("INSERT INTO collect_images (student_id, collect_url) VALUES (#{studentId}, #{collectUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(CollectImages image);

    @Select("SELECT * FROM collect_images WHERE student_id = #{studentId} ORDER BY id DESC LIMIT 1")
    CollectImages findLatestByStudentId(String studentId);
}
