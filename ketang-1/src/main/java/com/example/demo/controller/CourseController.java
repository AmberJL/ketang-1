package com.example.demo.controller;

import com.example.demo.Service.CourseLogService;
import com.example.demo.Service.CourseService;
import com.example.demo.data.courseData;
import com.example.demo.data.CourseMessage;
import com.example.demo.data.CourseWithNumTea;
import com.example.demo.data.courseLogData;
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
    @Autowired
    CourseLogService courseLogService;

    //教师创建课程
    @CrossOrigin
    @PostMapping(value = "/insertCourse")
    @ResponseBody
    public int InsertCourse(@RequestBody courseData coursedata) {
        //插入课程后返回数字1，异常返回0
        return this.courseService.insertCourse(coursedata);
    }

    //教师删除课程信息
    @CrossOrigin
    @PostMapping(value = "/teaDeleteCourse")
    @ResponseBody
    public int TeaDeleteCourse(@RequestBody courseData coursedata) {
        //删除课程后返回数字1，异常返回0
        return this.courseService.deleteCourse(coursedata);
    }

    //教师查询课程
    @CrossOrigin
    @PostMapping(value = "/teaSearchCourse")
    @ResponseBody
    public List<CourseWithNumTea> TeaSearchCourse(@RequestBody courseData courseData) {
        System.out.println("电话是" + courseData.getTeacher_phone());
        return this.courseService.searchCourseWithNumByPhone(courseData.getTeacher_phone());
    }

    //显示课程信息，错误则返回null
    @CrossOrigin
    @PostMapping(value = "/showCourseMessage")
    @ResponseBody
    public CourseMessage ShowCourseMessage(@RequestBody courseData data) {
        return courseService.findCourseMessageByCourseId(data);
    }

    //教师更新课程
    @CrossOrigin
    @PostMapping(value = "/teaUpdateCourse")
    @ResponseBody
    public int updateCourse(@RequestBody courseData courseData) {
        return courseService.UpdateCourse(courseData);
    }


}

