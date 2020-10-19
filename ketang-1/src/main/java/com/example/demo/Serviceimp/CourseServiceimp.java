package com.example.demo.Serviceimp;

import com.example.demo.Service.CourseService;
import com.example.demo.data.*;
import com.example.demo.entity.course_log_primaryKey;
import com.example.demo.entity.course_log_table;
import com.example.demo.entity.course_table;
import com.example.demo.entity.teacher_table;
import com.example.demo.repository.CourseLogRepositpry;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TeacherRespository;
import com.example.demo.tool.SJM;
import com.example.demo.tool.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceimp implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseLogRepositpry courseLogRepositpry;
    @Autowired
    TeacherRespository teacherRespository;
    course_log_primaryKey primaryKey;
    CourseWithNumTea courseWithNumTea;
    CourseWithNumStu courseWithNumStu;
    List<course_log_table> log;
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
            System.out.println("待删除的课程id"+course_id);
           // String teacher_phone = course.getTeacher_phone();
            if (courseLogRepositpry.selectCourseLogById(course_id) != null) {
                courseLogRepositpry.deleteCourseInLog(course_id);
                courseRepository.deleteCourse(course_id);
                return 1;
            } else{
                courseRepository.deleteCourse(course_id);
                return 1;
            }
        }catch (Exception e) {
            return 0;
        }
    }


    //教师通过手机号查看自己申报的课程
    public List<CourseWithNumTea> searchCourseWithNumByPhone(String phone) {
        System.out.println(phone);
        List<course_table> coursetable=courseRepository.findAllByTeacherphoneOrderByTimeDesc(phone);
        System.out.println(coursetable);
        List<CourseWithNumTea> courseWithNumTea =new ArrayList();
        for(int i=0;i<coursetable.size();i++) {
            CourseWithNumTea courseWithNumTea1 = new CourseWithNumTea();
            course_table coursetable1 = coursetable.get(i);
            courseWithNumTea1.setCourse_id(coursetable1.getCourseid());
            courseWithNumTea1.setCourse_name(coursetable1.getCoursename());
            courseWithNumTea1.setCourse_introduce(coursetable1.getCourseintroduce());
            courseWithNumTea1.setTeacher_phone(coursetable1.getTeacherphone());
            courseWithNumTea1.setNum(courseLogRepositpry.countByCourseid(coursetable1.getCourseid()));
            courseWithNumTea1.setTime(coursetable1.getTime().substring(0,10));
            courseWithNumTea.add(courseWithNumTea1);
        }
        System.out.println(courseWithNumTea);
        return courseWithNumTea;
    }


    //学生通过学生手机号查询课程信息
    public List<CourseWithNumStu> searchCourseByStuPhone(String stu_phone){
     List<String> course_id=new ArrayList<String>();
     List<course_table> courseDataList = new ArrayList<course_table>();
     List<CourseWithNumStu> list = new ArrayList<CourseWithNumStu>();
        try{
             log = courseLogRepositpry.findByStudentphone(stu_phone);
             System.out.println("输出查询的手机号"+stu_phone);
             for(int i=0;i<log.size();i++){
                 course_id.add(log.get(i).getCourseid());
             }
            System.out.println("输出查询的课程号"+course_id);
             for(int i=0;i<course_id.size();i++){
                 courseDataList.add(courseRepository.findByCourseid(course_id.get(i)));
             }
             System.out.println("输出课程信息："+courseDataList);
            for(int i=0;i<courseDataList.size();i++){
                CourseWithNumStu courseWithNumStu = new CourseWithNumStu();
                course_table courseData1 = courseDataList.get(i);
                courseWithNumStu.setCourse_id(courseData1.getCourseid());
                courseWithNumStu.setCourse_name(courseData1.getCoursename());
                courseWithNumStu.setCourse_introduce(courseData1.getCourseintroduce());
                courseWithNumStu.setNum(courseLogRepositpry.countByCourseid(courseData1.getCourseid()));
                courseWithNumStu.setTime(courseData1.getTime().substring(0,10));
                courseWithNumStu.setStudent_phone(stu_phone);
                list.add(courseWithNumStu);
            }
            System.out.println(list);

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }
        return list;
    }
    //通过课程号返回课程信息
    @Override
    public CourseMessage findCourseMessageByCourseId(courseData data) {
        teacher_table teacherTable ;
        course_table courseTable ;
        String tea_phone;
        try{
            courseTable = courseRepository.findByCourseid(data.getCourse_id());
            tea_phone = courseTable.getTeacherphone();
            teacherTable = teacherRespository.findByPhone(tea_phone);
            CourseMessage courseMessage = new CourseMessage();
            courseMessage.setCourse_introduce(courseTable.getCourseintroduce());
            courseMessage.setTeacher_name(teacherTable.getTeachername());
            System.out.println(courseMessage);
            return courseMessage;
        }catch (Exception e){
            return null;
        }
    }
    //更新课程，成功返回1，失败返回0
    @Override
    public int UpdateCourse(courseData data) {
    System.out.println(data);
        try{
            String course_name = data.getCourse_name();
            String course_introduce = data.getCourse_introduce();
            String course_id = data.getCourse_id();
            courseRepository.updateCourse(course_name,course_introduce,course_id);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
