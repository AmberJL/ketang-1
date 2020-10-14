package com.example.demo.controller;

import com.example.demo.Service.CourseService;
import com.example.demo.data.courseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class CourseController {
    @Autowired
    CourseService courseService;
    @CrossOrigin
    @PostMapping(value = "/insertCourse")
    @ResponseBody
    public int InsertCourse(@RequestBody courseData coursedata){
        //插入课程后返回数字1
        return this.courseService.insertCourse(coursedata);
    }
}
