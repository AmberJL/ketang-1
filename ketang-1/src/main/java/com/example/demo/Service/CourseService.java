package com.example.demo.Service;

import com.example.demo.data.courseData;
import com.example.demo.entity.CoursePackage.CourseWithNum;

import java.util.List;

public interface CourseService {
    int insertCourse(courseData course);
    int deleteCourse(courseData course);
    List<CourseWithNum> searchCourseWithNumByPhone(String phone);
}
