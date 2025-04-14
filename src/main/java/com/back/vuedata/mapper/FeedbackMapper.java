package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FeedbackMapper {

   // 根据学号查找反馈
   @Select("SELECT * FROM feedback WHERE student_id = #{studentId}")
   List<Feedback> findByStudentId(String studentId);

   // 插入反馈
   @Insert("INSERT INTO feedback (subject, content, phone_number, status, student_id, created_at, updated_at) " +
           "VALUES (#{subject}, #{content}, #{phoneNumber}, #{status}, #{studentId}, #{createdAt}, #{updatedAt})")
   void insertFeedback(Feedback feedback);

   // 根据ID更新反馈状态
   @Update("UPDATE feedback SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
   int updateFeedbackStatusById(int id, int status);
} 