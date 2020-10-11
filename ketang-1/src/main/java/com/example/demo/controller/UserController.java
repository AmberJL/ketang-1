package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.c.AES;
import com.example.demo.c.MD5;
import com.example.demo.c.user;
import com.example.demo.data.userData;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class UserController {
	
    @Autowired
    UserService userService;
	
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
		//登录
		return this.userService.forget_pwd(user);
	}
}
