package com.back.vuedata.controller;

import com.back.vuedata.pojo.Applications;
import com.back.vuedata.pojo.Result;
import com.back.vuedata.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    @Autowired
    private ApplicationsService applicationsService;

    @PostMapping("/submit")
    public Result submitApplication(@RequestParam String studentId, @RequestParam String applyReason) {
        Applications application = new Applications();
        application.setStudentId(studentId);
        application.setApplyReason(applyReason);
        applicationsService.submitApplication(application);
        return Result.success("申请提交成功");
    }
}
