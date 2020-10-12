package com.example.demo.Serviceimp;

import com.example.demo.Service.StudentService;
import com.example.demo.entity.school_table;
import com.example.demo.entity.student_table;
import com.example.demo.entity.user_table;
import com.example.demo.repository.SchoolRespository;
import com.example.demo.repository.StudentRespository;
import com.example.demo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentServiceimp implements StudentService {
    @Autowired
    UserRespository userRespository;
    @Autowired
    StudentRespository studentRespository;
    @Autowired
    SchoolRespository schoolRespository;
    @Override
    public void insertStudent(String stu_name, String sex, String year, int school_id, String stu_phone) {
        student_table studentTable = new student_table();
        try{
            user_table userTable = userRespository.findById(stu_phone).get();
            school_table schoolTable = schoolRespository.findById(school_id).get();
            if(userTable
                    .getIdentity().equals("T")){
                System.out.println("身份存在问题！");
            }
            else{
                studentTable.setStu_name(stu_name);
                studentTable.setSex(sex);
                studentTable.setYear(year);
                studentTable.setSchool_id(school_id);
                studentTable.setPhone(stu_phone);
                studentRespository.save(studentTable);
                System.out.println("学生信息插入成功！");
            }
        }
        catch (NoSuchElementException Exception){
                System.out.println("没有该用户！");
        }
    }
}
