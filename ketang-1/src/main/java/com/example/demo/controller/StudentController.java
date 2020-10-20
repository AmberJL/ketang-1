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
import com.example.demo.data.studentData;
import com.example.demo.data.teacherData;
import com.example.demo.data.userData;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class StudentController {
	@Autowired
	StudentService student;
	
	@CrossOrigin
    @PostMapping(value = "/insertStu")
    @ResponseBody
    public int InsertStudent(@RequestBody studentData data){
        return student.insertStudent(data);
    }
}
