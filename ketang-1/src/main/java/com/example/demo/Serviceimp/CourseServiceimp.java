package com.example.demo.Serviceimp;

import com.example.demo.Service.CourseService;
import com.example.demo.data.courseData;
import com.example.demo.entity.CoursePackage.CourseWithNum;
import com.example.demo.entity.course_log_primaryKey;
import com.example.demo.entity.course_table;
import com.example.demo.repository.CourseLogRepositpry;
import com.example.demo.repository.CourseRepository;
import com.example.demo.tool.SJM;
import com.example.demo.tool.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceimp implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseLogRepositpry courseLogRepositpry;
    course_log_primaryKey primaryKey;
    CourseWithNum courseWithNum;
   // List list;
    //教师添加课程相关信息
    @Override
    public int insertCourse(courseData coursedata) {
        course_table course = new course_table();
        String courseId = SJM.getNumLet(6);
    //数据库出现异常则返回0
        try {
            //判断课程号码是否重复,如果重复则直到获取不重复为止
            while (courseRepository.selectCourseById(courseId).size() != 0) {
                    courseId = SJM.getNumLet(6);
                    System.out.println(courseId);
            }
            course.setCourseid(courseId);
            course.setCoursename(coursedata.getCourse_name());
            course.setTeacherphone(coursedata.getTeacher_phone());
            course.setCourseintroduce(coursedata.getCourse_introduce());
            course.setTime(Time.getDay());
           // System.out.println(course);
            courseRepository.save(course);
            System.out.println("插入成功！");
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteCourse(courseData course) {
        //教师删除课程，成功则返回1，存在异常则返回0
        try {
            String course_id = course.getCourse_id();
           // String teacher_phone = course.getTeacher_phone();
            if (courseLogRepositpry.selectCourseLogById(course_id) != null) {
                courseLogRepositpry.deleteCourseInLog(course_id);
                courseRepository.deleteCourse(course_id);
                return 1;
            } else
                courseRepository.deleteCourse(course_id);
            return 1;
        }catch (Exception e) {
            return 0;
        }
    }


    public List<CourseWithNum> searchCourseWithNumByPhone(String phone) {
        System.out.println(phone);
        List<course_table> coursetable=courseRepository.findAllByTeacherphoneOrderByTimeDesc(phone);
        System.out.println(coursetable);
        List<CourseWithNum> courseWithNum=new ArrayList();
        for(int i=0;i<coursetable.size();i++) {
            CourseWithNum courseWithNum1 = new CourseWithNum();
            course_table coursetable1 = coursetable.get(i);
            courseWithNum1.setCourse_id(coursetable1.getCourseid());
            courseWithNum1.setCourse_name(coursetable1.getCoursename());
            courseWithNum1.setCourse_introduce(coursetable1.getCourseintroduce());
            courseWithNum1.setTeacher_phone(coursetable1.getTeacherphone());
            courseWithNum1.setNum(courseLogRepositpry.countByCourseid(coursetable1.getCourseid()));
            courseWithNum1.setTime(coursetable1.getTime().substring(0,10));
            courseWithNum.add(courseWithNum1);
        }
        System.out.println(courseWithNum);
        return courseWithNum;
    }
}
