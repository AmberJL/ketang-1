package com.example.demo.controller;

import com.example.demo.Service.CourseLogService;
import com.example.demo.data.courseLogData;
import com.example.demo.repository.CourseLogRepositpry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class CourseLogController {
    @Autowired
    CourseLogService courseLogService;
    @CrossOrigin
    @PostMapping(value = "/insertCourseLog")
    @ResponseBody
    public int InsertCourseLog(@RequestBody courseLogData courseLogData){
        return courseLogService.insertCourseLog(courseLogData);
    }
}
