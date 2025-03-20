package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedbackMapper {

   //查找反馈
   @Select("SELECT * FROM feedback")
   List<Feedback> findAll();
} 