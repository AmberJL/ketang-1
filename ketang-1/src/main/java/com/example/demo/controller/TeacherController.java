package com.example.demo.controller;

import com.example.demo.Service.TeacherService;
import com.example.demo.data.teacherData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    //教师插入信息，若用户不存在则返回100，没有学校返回101，身份存在问题返回110，插入成功返回111
    @CrossOrigin
    @PostMapping(value = "/insertTea")
    @ResponseBody
    public int InsertTeacher(@RequestBody teacherData data){
        return teacherService.insertTeacher(data);
    }
}
