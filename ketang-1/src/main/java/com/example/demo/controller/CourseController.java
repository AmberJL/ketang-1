package com.example.demo.controller;

import com.example.demo.Service.CourseService;
import com.example.demo.data.courseData;
import com.example.demo.entity.CoursePackage.CourseWithNum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //插入课程后返回数字1，异常返回0
        return this.courseService.insertCourse(coursedata);
    }
    @CrossOrigin
    @PostMapping(value = "/teaDeleteCourse")
    @ResponseBody
    public int TeaDeleteCourse(@RequestBody courseData coursedata){
        //插入课程后返回数字1，异常返回0
        return this.courseService.deleteCourse(coursedata);
    }
    @CrossOrigin
    @PostMapping(value = "/teaSearchCourse")
    @ResponseBody
    public List<CourseWithNum> TeaSearchCourse(@RequestBody courseData courseData){
        System.out.println("电话是"+courseData.getTeacher_phone());
        return this.courseService.searchCourseWithNumByPhone(courseData.getTeacher_phone());
    }
}

