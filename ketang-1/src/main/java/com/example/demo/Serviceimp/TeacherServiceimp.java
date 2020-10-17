package com.example.demo.Serviceimp;

import com.example.demo.Service.TeacherService;
import com.example.demo.entity.school_table;
import com.example.demo.entity.teacher_table;
import com.example.demo.entity.user_table;
import com.example.demo.repository.SchoolRespository;
import com.example.demo.repository.TeacherRespository;
import com.example.demo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TeacherServiceimp implements TeacherService {
    @Autowired
    TeacherRespository teacherRespository;
    @Autowired
    UserRespository userRespository;
    @Autowired
    SchoolRespository schoolRespository;

    @Override

    public void insertTeacher( String name, String sex, String department, int school_id, String phone) {
        teacher_table teacherTable = new teacher_table();

        try{
            user_table userTable = userRespository.findById(phone).get();
            school_table schoolTable = schoolRespository.findById(school_id).get();
            if(userTable
                    .getIdentity().equals("S")){
                System.out.println("身份存在问题！");
            }
            else {
                teacherTable.setTeachername(name);
                teacherTable.setSex(sex);
                teacherTable.setDepartment(department);
                teacherTable.setSchoolid(school_id);
                teacherTable.setPhone(phone);
                teacherRespository.save(teacherTable);
                System.out.println("插入教师表成功！");
            }

        }catch (NoSuchElementException Exception){
            System.out.println("没有该用户！");
        }
    }
}
