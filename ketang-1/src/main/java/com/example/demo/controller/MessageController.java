package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.MessageService;
import com.example.demo.Service.UserService;
import com.example.demo.data.messageData;
import com.example.demo.data.userData;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class MessageController {
	@Autowired
    MessageService messageService;
	@CrossOrigin
	@PostMapping(value = "/unReadMessage")
	@ResponseBody
	public List<messageData> signup(@RequestParam(value = "id")String id) {
		//未读消息
		return this.messageService.unReadMessage(id);
	}
	@CrossOrigin
	@PostMapping(value = "/allHistoryMessage")
	@ResponseBody
	public List<messageData> allHistoryMessage(@RequestBody messageData msd) {
		//全部历史消息
		return this.messageService.allHistoryMessage(msd.getTo_user_id());
	}
}
