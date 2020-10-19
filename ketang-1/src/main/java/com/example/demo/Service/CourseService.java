package com.example.demo.Service;

import com.example.demo.data.*;

import java.util.List;

public interface CourseService {
    //教师插入课程
    int insertCourse(courseData course);
    //教师删除课程
    int deleteCourse(courseData course);
    //通过教师手机号查询课程
    List<CourseWithNumTea> searchCourseWithNumByPhone(String phone);
    //通过学生手机号查询课程
    List<CourseWithNumStu> searchCourseById(String course_id,String phone);
    //通过学生手机号查询选修的课程
    List<CourseWithNumStu> searchCourseByStuPhone(String stu_phone);
    //通过课程号查询课程信息
    CourseMessage findCourseMessageByCourseId(courseData data);
    //教师更新课程信息
    int UpdateCourse(courseData course);
}
