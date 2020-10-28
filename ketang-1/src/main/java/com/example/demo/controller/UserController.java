package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.StudentService;
import com.example.demo.Service.TeacherService;
import com.example.demo.Service.UserService;
import com.example.demo.c.AES;
import com.example.demo.c.MD5;
import com.example.demo.c.user;
import com.example.demo.data.UData;
import com.example.demo.data.studentData;
import com.example.demo.data.teacherData;
import com.example.demo.data.userData;
import com.example.demo.entity.teacher_table;
import com.example.demo.entity.user_table;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class UserController {
	
    @Autowired
    UserService userService;
    @Autowired
    TeacherService teaService;
    @Autowired
    StudentService stuService;
	
	@CrossOrigin
	@PostMapping(value = "/signup")
	@ResponseBody
	public int signup(@RequestBody userData user) {
		//注册
		return this.userService.signUp(user);
	}
	@CrossOrigin
	@PostMapping(value = "/login")
	@ResponseBody
	public int login(@RequestBody userData user) {
		//登录
		return this.userService.login(user);
	}
	@CrossOrigin
	@PostMapping(value = "/forget_pwd")
	@ResponseBody
	public int forget_pwd(@RequestBody userData user) {
		//忘记密码
		HashMap<String,String> t = new HashMap();
		t.put("key", user.getKey());
		t.put("value", user.getUser_phone());
		String phone = AES.decode(t);
		System.out.println("我是:"+phone+"修改密码controller");
		return this.userService.forget_pwd(user);
	}
	@CrossOrigin
	@PostMapping(value = "/showUserInfo")
	@ResponseBody
	public UData showUserInfo(@RequestBody userData user) {
		String identity = this.userService.showIdentity(user.getUser_phone());
		UData u = new UData();
		if(identity.equals("T"))
		{
			teacherData teaData = new teacherData();
			teaData.setTeacher_phone(user.getUser_phone());
			teaData = this.teaService.showInfo(teaData);
			BeanUtils.copyProperties(teaData, u);
		}else {
			studentData stu = new studentData();
			stu.setStu_phone(user.getUser_phone());
			stu = this.stuService.showInfo(stu);
			System.out.println(stu.getStu_phone()+"\n"+stu.getStu_name());
			BeanUtils.copyProperties(stu, u);
		}
		
		return u;
		
	}
	@CrossOrigin
	@PostMapping(value = "/updateInfo")
	@ResponseBody
	public int updateInfo(@RequestBody userData user) {
		String identity = this.userService.showIdentity(user.getUser_phone());
		if(identity.equals("T")) {
			
		}else {
			
		}
		return 0;
	}
}
