package com.example.demo.Serviceimp;

import com.example.demo.Service.CourseService;
import com.example.demo.data.courseData;
import com.example.demo.entity.course_table;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceimp implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    //教师添加课程相关信息
    @Override
    public int insertCourse(courseData coursedata) {
        course_table course = new course_table();
        String courseId = coursedata.getCourse_id();
        String courseName = coursedata.getCourse_name();
        String tea_phone = coursedata.getTeacher_phone();
        course.setCourse_id(courseId);
        course.setCourse_name(courseName);
        course.setTeacher_phone(tea_phone);
        courseRepository.save(course);
        System.out.println("插入成功！");
        return 1;
    }
}
