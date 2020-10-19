package com.example.demo.Serviceimp;

import com.example.demo.Service.CourseLogService;
import com.example.demo.Service.CourseService;
import com.example.demo.data.courseLogData;
import com.example.demo.entity.course_log_table;
import com.example.demo.entity.course_table;
import com.example.demo.repository.CourseLogRepositpry;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLogServiceimp implements CourseLogService {
    @Autowired
    CourseLogRepositpry courseLogRepositpry;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseService courseService;

    course_log_table courseLog;
    //学生选修课程，正常插入课程返回1，否则返回0
    @Override
    public int insertCourseLog(courseLogData log) {
        String course_id;
        courseLog = new course_log_table();
        try {
            course_id = courseRepository.findByCourseid(log.getCourse_id()).getCourseid();
        }catch (Exception e){
            return 10;
        }
        courseLog.setCourseid(course_id);
        courseLog.setStudentphone(log.getStu_phone());
        try {
            courseLogRepositpry.save(courseLog);
        }
        catch (Exception e) {
            return 0;
        }
        return 11;
    }
    //通过学生手机号查询课程id
    @Override
    public String searchCourseByStuId(courseLogData logData) {
        String phone = logData.getStu_phone();
        courseLog = courseLogRepositpry.findByStudentphone(phone);
        return courseLog.getCourseid();
    }
    //学生删除选课
    @Override
    public int deleteCourseLog(courseLogData courseLog) {
        String course_id,stu_phone;
        try{
            course_id = courseLog.getCourse_id();
            stu_phone = courseLog.getStu_phone();
            System.out.println("课程号是"+course_id+"\n"+"学生手机号是"+stu_phone);
            courseLogRepositpry.deleteByCourseidAndStudentphone(course_id,stu_phone);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
