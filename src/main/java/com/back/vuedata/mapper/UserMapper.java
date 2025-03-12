package com.back.vuedata.mapper;

import com.back.vuedata.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE student_id = #{studentId}")
    User findUserByStudentId(@Param("studentId") String studentId);

    @Insert("INSERT INTO user(student_id, password, phone_number, nick_name, avatar_url) " +
            "VALUES(#{studentId}, #{password}, #{phoneNumber}, #{nickName}, #{avatarUrl})")
    void insertUser(User user);

    @Update("UPDATE user SET password = #{newPassword} WHERE student_id = #{studentId}")
    void updatePassword(@Param("studentId") String studentId, @Param("newPassword") String newPassword);

    @Update("UPDATE user SET phone_number = #{phoneNumber}, nick_name = #{nickName}, avatar_url = #{avatarUrl} " +
            "WHERE student_id = #{studentId}")
    void updateProfile(User user);
}
