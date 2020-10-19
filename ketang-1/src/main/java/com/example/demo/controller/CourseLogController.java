package com.example.demo.controller;

import com.example.demo.Service.CourseLogService;
import com.example.demo.Service.CourseService;
import com.example.demo.data.courseLogData;
import com.example.demo.data.CourseWithNumStu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class CourseLogController {
    @Autowired
    CourseLogService courseLogService;
    @Autowired
    CourseService courseService;
    @CrossOrigin
    @PostMapping(value = "/insertCourseLog")
    @ResponseBody
    public int InsertCourseLog(@RequestBody courseLogData courseLogData){
        System.out.println(courseLogData.getStu_phone());
        System.out.println(courseLogData.getCourse_id());
        return courseLogService.insertCourseLog(courseLogData);
    }
    //通过学生手机号查询已经选修的课程
    @CrossOrigin
    @PostMapping(value = "/stuSearchCourse")
    @ResponseBody
    public List<CourseWithNumStu> SearchCourseLog(@RequestBody courseLogData courseLogData){
        System.out.println(courseLogData.getStu_phone());
       // System.out.println(courseLogData.getCourse_id());
        return courseService.searchCourseByStuPhone(courseLogData.getStu_phone());
    }
    //学生删除选课信息
    @CrossOrigin
    @PostMapping(value = "/stuDeleteCourseLog")
    @ResponseBody
    public int stuDeleteCourseLog(@RequestBody courseLogData data) {
        //删除课程后返回数字1，异常返回0
        return this.courseLogService.deleteCourseLog(data);
    }
}
