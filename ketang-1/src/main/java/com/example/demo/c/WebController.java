package com.example.demo.c;

import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@CrossOrigin
public class WebController {
	@CrossOrigin
	@GetMapping(value = "/push")
	@ResponseBody
	public int push() {
		RedisClient c = new RedisClient();
		return c.push("myList", "你好");
	}
	@CrossOrigin
	@GetMapping(value = "/test")
	@ResponseBody
	public int test() {
		
		return 1;
	}
	@CrossOrigin
	@PostMapping(value = "/send")
	@ResponseBody
	public int send(@RequestBody MessageData ms) {
		RedisClient c = new RedisClient();
		System.out.println(ms.getMsgData()+"fid "+ms.getFromUserId());
		return c.push("myList", ms.getMsgData());
	}
//	@CrossOrigin
//	@PostMapping(value = "/login")
//	@ResponseBody
//	public int login(@RequestBody user user) {
//		String pwd = user.getPassword();
//		System.out.println(user.getName()+" get "+ pwd + "key:"+user.getKey()+" phone:"+user.getPhone());
//		pwd = MD5.md5_salt(pwd);
//		System.out.println(user.getName()+" final: "+ pwd);
//		HashMap<String,String> t = new HashMap();
//		t.put("key", user.getKey());
//		t.put("value", user.getPhone());
//		String phone = AES.decode(t);
//		System.out.println("phone:"+phone+" identity:"+user.getIdentity());
//		return 1;
//	}
}
