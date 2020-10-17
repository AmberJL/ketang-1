package com.example.demo.Serviceimp;

import com.example.demo.Service.CourseLogService;
import com.example.demo.data.courseLogData;
import com.example.demo.entity.course_log_table;
import com.example.demo.repository.CourseLogRepositpry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLogServiceimp implements CourseLogService {
    @Autowired
    CourseLogRepositpry courseLogRepositpry;
    course_log_table courseLog;
    //学生选修课程，正常插入课程返回1，否则返回0
    @Override
    public int insertCourseLog(courseLogData log) {
        courseLog = new course_log_table();
        courseLog.setCourseid(log.getCourse_id());
        courseLog.setStudentphone(log.getStu_phone());
        try {
            courseLogRepositpry.save(courseLog);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
