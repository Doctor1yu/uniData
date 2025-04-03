package com.back.vuedata.service;

import com.back.vuedata.pojo.User;

public interface UserService {
    // 用户注册
    User register(User user) throws RuntimeException;

    // 用户登录
    User login(String studentId, String password) throws RuntimeException;

    // 修改密码
    void changePassword(String studentId, String oldPassword, String newPassword, String confirmPassword) throws RuntimeException;

    // 更新用户信息
    User updateProfile(User user) throws RuntimeException;

    // 根据学号查询用户信息
    User findUserByStudentId(String studentId);

    //根据学号查询申请状态
    String findApplicationStatusByStudentId(String studentId);
}
