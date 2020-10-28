package com.example.demo.Serviceimp;

import com.example.demo.Service.TeacherService;
import com.example.demo.data.schoolData;
import com.example.demo.data.teacherData;
import com.example.demo.data.userData;
import com.example.demo.entity.school_table;
import com.example.demo.entity.teacher_table;
import com.example.demo.entity.user_table;
import com.example.demo.repository.SchoolRespository;
import com.example.demo.repository.TeacherRespository;
import com.example.demo.repository.UserRespository;

import org.springframework.beans.BeanUtils;
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

    public int insertTeacher(teacherData teaData) {
        user_table userTable;
        school_table schoolTable;
        String phone = teaData.getTeacher_phone();
        int school_id = teaData.getSchool_id();
        String name = teaData.getTeacher_name();
        String sex = teaData.getSex().equals("man") ? "B" :"G";
        String department = teaData.getDepartment();
        String tea_id = teaData.getTeacher_id();
        teacher_table teacherTable = new teacher_table();

        try{
            userTable = userRespository.findByUserphone(phone);
        }catch (Exception e){
            System.out.println("没有该用户！");
            return 100;
        }
//        try{
//            schoolTable = schoolRespository.findBySchoolid(school_id);
//        }catch (Exception e){
//            System.out.println("没有该学校！");
//            return 101;
//        }

        if(userTable.getIdentity().equals("S")){
            System.out.println("身份存在问题！");
            return 110;
            }
            else {
                teacherTable.setTeachername(name);
                teacherTable.setSex(sex);
                teacherTable.setDepartment(department);
                teacherTable.setSchoolid(school_id);
                teacherTable.setPhone(userTable.getUserphone());
                teacherTable.setTeacherid(tea_id);
                teacherTable.setPic_id(teaData.getPic_id());
                teacherRespository.save(teacherTable);
                System.out.println("插入教师表成功！");
                return 200;
            }
    }

	@Override
	public teacherData showInfo(teacherData teaData) {
		// TODO Auto-generated method stub
		teacher_table t = this.teacherRespository.findByPhone(teaData.getTeacher_phone());
		teacherData te = new teacherData();
		te.setPic_id(t.getPic_id());
		te.setSchool_id(t.getSchoolid());
		te.setDepartment(t.getDepartment());
		te.setSex(t.getSex());
		te.setTeacher_id(t.getTeacherid());
		te.setTeacher_name(t.getTeachername());
		te.setTeacher_phone(t.getPhone());
		return te;
	}

	@Override
	public int updateTInfo(teacherData teaData) {
		// TODO Auto-generated method stub
		
		try {
			String sex = teaData.getSex().equals("B") ? "B" : "G";
			this.teacherRespository.updateInfo(teaData.getPic_id(), teaData.getTeacher_name(), sex, teaData.getSchool_id(), teaData.getTeacher_id(), teaData.getDepartment(), teaData.getTeacher_phone());
			System.out.println("tea:"+teaData.getTeacher_phone()+" 修改信息成功\n");
			return 200;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 199;
		}
	}
}
