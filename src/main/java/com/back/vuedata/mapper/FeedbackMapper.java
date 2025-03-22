package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedbackMapper {

   //发布订单

   // 根据学号查找反馈
   @Select("SELECT * FROM feedback WHERE student_id = #{studentId}")
   List<Feedback> findByStudentId(String studentId);

   // 插入反馈
   @Insert("INSERT INTO feedback (subject, content, phone_number, status, student_id, created_at, updated_at) " +
           "VALUES (#{subject}, #{content}, #{phoneNumber}, #{status}, #{studentId}, #{createdAt}, #{updatedAt})")
   void insertFeedback(Feedback feedback);
} 