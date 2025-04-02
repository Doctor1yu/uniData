package com.back.vuedata.service.impl;

import com.back.vuedata.mapper.UserMapper;
import com.back.vuedata.pojo.User;
import com.back.vuedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户注册
    @Override
    public User register(User user) throws RuntimeException {
        // 检查学号是否已存在
        User existingUser = userMapper.findUserByStudentId(user.getStudentId());
        if (existingUser != null) {
            throw new RuntimeException("该学号已注册");
        }
        // 插入新用户
        userMapper.insertUser(user);
        return user;
    }

    //用户登陆
    @Override
    public User login(String studentId, String password) throws RuntimeException {
        User user = userMapper.findUserByStudentId(studentId);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("学号或密码错误");
        }
        return user;
    }

    //修改密码
    @Override
    public void changePassword(String studentId, String oldPassword, String newPassword, String confirmPassword) throws RuntimeException {
        // 检查新密码和确认密码是否一致
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("新密码和确认密码不一致");
        }
        
        // 添加密码长度验证
        if (newPassword.length() < 6 || newPassword.length() > 20) {
            throw new RuntimeException("密码长度应在6到20个字符之间");
        }

        // 查询用户信息
        User user = userMapper.findUserByStudentId(studentId);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("原密码错误");
        }

        // 更新密码
        userMapper.updatePassword(studentId, newPassword);
    }

    @Override
    public User updateProfile(User user) throws RuntimeException {
        // 先检查用户是否存在
        User existingUser = userMapper.findUserByStudentId(user.getStudentId());
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 更新用户信息
        userMapper.updateProfile(user);
        return userMapper.findUserByStudentId(user.getStudentId());
    }

    @Override
    public User findUserByStudentId(String studentId) {
        return userMapper.findUserByStudentId(studentId);
    }
}
