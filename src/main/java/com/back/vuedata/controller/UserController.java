package com.back.vuedata.controller;

import com.back.vuedata.pojo.Result;
import com.back.vuedata.pojo.User;
import com.back.vuedata.service.UserService;
import com.back.vuedata.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationsService applicationsService;

    // 用户注册功能
    @PostMapping("/register")
    public Result register(User user) {
        try {
            User registeredUser = userService.register(user);
            return Result.success(registeredUser);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 用户登录功能，同时返回用户的申请状态
    @PostMapping("/login")
    public Result login(@RequestParam String studentId, @RequestParam String password) {
        try {
            User user = userService.login(studentId, password);
            
            // 查询申请状态
            String status = applicationsService.findStatusByStudentId(studentId);
            
            // 将用户信息和申请状态一起返回
            user.setApplicationStatus(status); // 假设 User 类中添加了 applicationStatus 字段
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 修改密码功能
    @PatchMapping("/changePassword")
    public Result changePassword(@RequestParam String studentId, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        try {
            userService.changePassword(studentId, oldPassword, newPassword, confirmPassword);
            return Result.success("密码修改成功");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 更新用户个人信息功能
    @PutMapping("/updateProfile")
    public Result updateProfile(@RequestParam String studentId, @RequestParam String nickName, @RequestParam String phoneNumber) {
        try {
            User user = new User();
            user.setStudentId(studentId);
            user.setNickName(nickName);
            user.setPhoneNumber(phoneNumber);

            User updatedUser = userService.updateProfile(user);
            return Result.success(updatedUser);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    //根据学号查找学生信息
    @GetMapping("/find")
    public Result findUserByStudentId(@RequestParam String studentId) {
        try {
            User user = userService.findUserByStudentId(studentId);
            if (user == null) {
                return Result.error("查无此学号");
            }
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
}
