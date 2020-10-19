package com.example.demo.Serviceimp;

import com.example.demo.Service.StudentService;
import com.example.demo.data.studentData;
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
    public void insertStudent(studentData stu) {
        student_table studentTable = new student_table();
        try{
            user_table userTable = userRespository.findById(stu.getStu_phone()).get();
            school_table schoolTable = schoolRespository.findById(stu.getSchool_id()).get();
            if(userTable
                    .getIdentity().equals("T")){
                System.out.println("身份存在问题！");
            }
            else{
                studentTable.setStu_id(stu.getStu_id());
                studentTable.setStu_name(stu.getStu_name());
                studentTable.setSex(stu.getSex());
                studentTable.setYear(stu.getYear());
                studentTable.setSchoolTable(schoolTable);
                studentTable.setUserTable(userTable);
                studentRespository.save(studentTable);
                System.out.println("学生信息插入成功！");
            }
        }
        catch (NoSuchElementException Exception){
                System.out.println("没有该用户！");
        }
    }
}
