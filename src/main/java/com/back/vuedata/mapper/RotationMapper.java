package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Rotation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RotationMapper {

    @Select("SELECT id, theme, rotation_url FROM rotation")
    List<Rotation> selectAll();
}
