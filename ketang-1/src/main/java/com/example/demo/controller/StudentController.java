package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.StudentService;
import com.example.demo.c.AES;
import com.example.demo.data.userData;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class StudentController {
	@Autowired
	StudentService student;
	
	@CrossOrigin
	@PostMapping(value = "/infoCheck")
	@ResponseBody
	public int infoCheck(@RequestBody userData user) {
		//检查用户是否完善过信息
		HashMap<String,String> t = new HashMap();
		t.put("key", user.getKey());
		t.put("value", user.getUser_phone());
		String phone = AES.decode(t);
		System.out.println("我是:"+phone+"修改密码controller");
		return 100;
	}
}
