package com.back.vuedata.mapper;

import com.back.vuedata.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //根据学号查找用户
    @Select("SELECT * FROM user WHERE student_id = #{studentId}")
    User findUserByStudentId(@Param("studentId") String studentId);

    //注册
    @Insert("INSERT INTO user(student_id, password, phone_number, nick_name, avatar_url) " +
            "VALUES(#{studentId}, #{password}, #{phoneNumber}, #{nickName}, #{avatarUrl})")
    void insertUser(User user);

    //根据学号更新密码(有问题）
    @Update("UPDATE user SET password = #{newPassword}, updated_at = now() WHERE student_id = #{studentId}")
    void updatePassword(@Param("studentId") String studentId, @Param("newPassword") String newPassword);

    //根据学号更新信息
    @Update("UPDATE user SET phone_number = #{phoneNumber}, nick_name = #{nickName}, updated_at = now()" +
            "WHERE student_id = #{studentId}")
    void updateProfile(User user);
}
