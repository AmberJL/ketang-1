package com.example.demo.controller;

import com.example.demo.Service.AdminService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.TeacherService;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@Slf4j

public class testUpdate {
    //测试登录
    @Autowired
    UserService userService ;
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @GetMapping("/getLogin")
    @ResponseBody
    public String testLogin() {
        String phone = "12345";
        String pwd = "123";
        //userService.Testlogin(phone, pwd);
        //adminService.InsertAdmin(1,"zzz","123","12345");
        teacherService.insertTeacher(1,"zzz","B","软件工程",1,"12");
        teacherService.insertTeacher(2,"zzh","G","软件工程",2,"123");
        teacherService.insertTeacher(3,"zzh","G","软件工程",1,"123");
//        studentService.insertStudent(123,"zzh","B","2017",1,"12");
        return "查询成功";
    }
}
