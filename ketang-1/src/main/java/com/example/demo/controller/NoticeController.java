package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.NoticeService;
import com.example.demo.Serviceimp.NoticeServiceimp;
import com.example.demo.entity.notice_table;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	
	public NoticeController(@Autowired NoticeServiceimp noticeService) {
		this.noticeService=noticeService;
	}
	
	//老师添加公告
	@RequestMapping("/add")
	public String add(@RequestBody pack.addPara p) {
		
		String course_id=p.course_id;
		String value=p.value;
		String title=p.title;
		
		String temp=noticeService.add(course_id, value, title);
		
		if("1".equals(temp)) {
			// TODO
			/**
			 * 这里可以广播通知公告
			 * course_id为课程id
			 * 可以根据选课表查到学生列表依次广播
			 * 
			 */
		}
		
		return temp;
	}
	
	//获取公告列表
	@RequestMapping("/get")
	public List<?> get(@RequestBody pack.getPara p) {
		
		String course_id=p.course_id;
		
		return pack.parseNotice(noticeService.get(course_id));
	}
	
	//删除公告
	@RequestMapping("/remove")
	public String remove(@RequestBody pack.removePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return noticeService.remove(course_id, fb_time);
	}
	
	//修改公告
	@RequestMapping("/change")
	public String change(@RequestBody pack.changePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		String value=p.value;
		String title=p.title;
		
		return noticeService.change(course_id, fb_time, value, title);
	}
	
	
	
	
	//包装参数和返回值
	private static class pack{
		public static List<notice> parseNotice(List<notice_table> p){
			List<notice> temp=new ArrayList<notice>();
			for(int i=0;i<p.size();i++) {
				temp.add(new notice(p.get(i)));
			}
			return temp;
		}
		public static class notice{
			public String fb_time;
			public String value;
			public String title;
			public notice(notice_table p) {
				this.fb_time=p.getFbtime();
				this.value=p.getValue();
				this.title=p.getTitle();
			}
		}
		public static class addPara{
			public String course_id;
			public String value;
			public String title;
		}
		public static class getPara{
			public String course_id;
		}
		public static class removePara{
			public String course_id;
			public String fb_time;
		}
		public static class changePara{
			public String course_id;
			public String fb_time;
			public String title;
			public String value;
		}
	}
}
