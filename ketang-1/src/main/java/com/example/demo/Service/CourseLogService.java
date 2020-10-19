package com.example.demo.Service;

import com.example.demo.data.courseLogData;

public interface CourseLogService {
    int insertCourseLog(courseLogData log);
    String searchCourseByStuId(courseLogData logData);
    //学生删除课程
    int deleteCourseLog(courseLogData courseLog);

}
