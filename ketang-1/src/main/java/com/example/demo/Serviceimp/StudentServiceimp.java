package com.example.demo.Serviceimp;

import com.example.demo.Service.StudentService;
import com.example.demo.data.studentData;
import com.example.demo.entity.school_table;
import com.example.demo.entity.student_table;
import com.example.demo.entity.user_table;
import com.example.demo.repository.SchoolRespository;
import com.example.demo.repository.StudentRespository;
import com.example.demo.repository.UserRespository;

import org.springframework.beans.BeanUtils;
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
    public int insertStudent(studentData stu) {
        student_table studentTable = new student_table();
        try{
            user_table userTable = userRespository.findById(stu.getStu_phone()).get();
//            school_table schoolTable = schoolRespository.findById(stu.getSchool_id()).get();
            if(userTable
                    .getIdentity().equals("T")){
                System.out.println("身份存在问题！");
                return 199;
            }
            else{
            	String sex = stu.getSex().equals("man") ? "B" :"G";
                studentTable.setStuname(stu.getStu_name());
                studentTable.setSex(sex);
                studentTable.setYear("2017");
                studentTable.setSchoolid(stu.getSchool_id());
                studentTable.setPhone(stu.getStu_phone());
                studentTable.setStuid(stu.getStu_id());
                studentTable.setPic_id(stu.getPic_id());
                studentRespository.save(studentTable);
                System.out.println("学生信息插入成功！");
                return 200;
            }
        }
        catch (NoSuchElementException Exception){
        	Exception.printStackTrace();
                System.out.println("没有该用户！");
                return 199;
        }
    }
	@Override
	public studentData showInfo(studentData stu) {
		// TODO Auto-generated method stub
		student_table st = this.studentRespository.findByPhone(stu.getStu_phone());
		System.out.println("stu:"+st.getPhone());
		
		studentData std = new studentData();
		std.setPic_id(st.getPic_id());
		std.setSchool_id(st.getSchoolid());
		std.setSex(st.getSex());
		std.setStu_id(st.getStuid());
		std.setStu_name(st.getStuname());
		std.setStu_phone(st.getPhone());
		return std;
	}
	@Override
	public int updateSInfo(studentData stu) {
		// TODO Auto-generated method stub
		try {
			String sex = stu.getSex().equals("B") ? "B" : "G";
			this.studentRespository.updateInfo(stu.getPic_id(), stu.getStu_name(), sex, stu.getSchool_id(), stu.getStu_id(), stu.getStu_phone());
			System.out.println("stu:"+stu.getStu_phone()+" 修改信息成功\n");
			return 200;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 199;
		}
		
	}
}
