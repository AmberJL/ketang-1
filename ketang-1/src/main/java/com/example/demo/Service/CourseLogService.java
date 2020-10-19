package com.example.demo.Service;

import com.example.demo.data.courseLogData;
import com.example.demo.entity.course_log_table;

import java.util.List;

public interface CourseLogService {
    int insertCourseLog(courseLogData log);
    List<course_log_table> searchCourseByStuId(courseLogData logData);
    //学生删除课程
    int deleteCourseLog(courseLogData courseLog);

}
