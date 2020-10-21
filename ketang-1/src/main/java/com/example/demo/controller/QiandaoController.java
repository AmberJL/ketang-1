package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.QiandaoService;
import com.example.demo.Serviceimp.QiandaoServiceimp;
import com.example.demo.entity.qiandao_log_table;
import com.example.demo.entity.qiandao_table;

@RestController
@RequestMapping("/qiandao")
public class QiandaoController {
	
	private final QiandaoService qiandaoService;

	public QiandaoController(@Autowired QiandaoServiceimp qiandaoService) {
		this.qiandaoService=qiandaoService;
	}
	
	//老师获取签到列表
	@RequestMapping("/getListT")
	public List<?> getListT(@RequestBody pack.courseidPara p) {
		
		String course_id=p.course_id;
		
		List<?> temp=pack.parseQdListTeacher(qiandaoService.getListT(course_id));
		for(int i=0;i<temp.size();i++) {
			pack.qdListTeacher temp2=(pack.qdListTeacher)temp.get(i);
			temp2.qd_size=qiandaoService.getSize(course_id,temp2.fb_time);
		}
		return temp;
	}
	
	//发布签到
	@RequestMapping("/newQd")
	public String newQd(@RequestBody pack.fbqdPara p) {
		
		String course_id=p.course_id;
		String name=p.name;
		String way=p.name;
		long jz_long=p.jz_long;
		
		String temp=qiandaoService.newQd(course_id,name,way,jz_long);
		
		if(!"课程不存在".equals(temp)) {
		// TODO
		/**
		 * 这里可以广播通知签到
		 */
		}
		
		return temp;
	}
	
	//老师获取单次签到人数
	@RequestMapping("/getSize")
	public int getSize(@RequestBody pack.courseidTimePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return qiandaoService.getSize(course_id, fb_time);
	}
	
	//老师获取单次签到详情
	@RequestMapping("/getValue")
	public List<?> getValue(@RequestBody pack.courseidTimePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		List<?> temp=pack.parseQdInfoTeacher(qiandaoService.getValue(course_id, fb_time));
		
		// TODO
		/**
		 * 这里查询学生表添加学生姓名和学号
		 */
		
		return temp;
	}
	
	//老师修改签到状态
	@RequestMapping("/change")
	public String change(@RequestBody pack.changePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		String student_phone=p.student_phone;
		String value=p.value;
		
		return qiandaoService.change(course_id, fb_time, student_phone, value);
	}
	
	//老师删除签到
	@RequestMapping("/remove")
	public String remove(@RequestBody pack.courseidTimePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return qiandaoService.remove(course_id, fb_time);
	}
	
	//学生获取签到列表
	@RequestMapping("/getListS")
	public List<?> getListS(@RequestBody pack.courseidPhonePara p) {
		
		String student_phone=p.student_phone;
		String course_id=p.course_id;
		
		return pack.parseQdListStu(qiandaoService.getListS(student_phone, course_id));
	}
	
	//学生签到
	@RequestMapping("/qd")
	public String qd(@RequestBody pack.qdPara p) {
		
		String student_phone=p.student_phone;
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		String code=p.code;
		
		return qiandaoService.qd(student_phone, course_id, fb_time, code);
	}
	//老师重置签到时间
	@RequestMapping("/resetTime")
	public String resetTime(pack.resetTimePara p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		long jz_long=p.jz_long;
		
		return qiandaoService.resetTime(course_id, fb_time, jz_long);
	}
	
	
	
	
	
	//包装参数和返回值
	private static class pack{
		public static List<qdListTeacher> parseQdListTeacher(List<qiandao_table> p){
			List<qdListTeacher> temp=new ArrayList<qdListTeacher>();
			for(int i=0;i<p.size();i++) {
				temp.add(new qdListTeacher(p.get(i)));
			}
			return temp;
		}
		public static List<qdInfoTeacher> parseQdInfoTeacher(List<qiandao_log_table> p){
			List<qdInfoTeacher> temp=new ArrayList<qdInfoTeacher>();
			for(int i=0;i<p.size();i++) {
				temp.add(new qdInfoTeacher(p.get(i)));
			}
			return temp;
		}
		public static List<qdListStu> parseQdListStu(List<qiandao_table> p){
			List<qdListStu> temp=new ArrayList<qdListStu>();
			for(int i=0;i<p.size();i++) {
				temp.add(new qdListStu(p.get(i)));
			}
			return temp;
		}
		public static class qdListTeacher{
			public String fb_time;
			public String jz_time;
			public String name;
			public String way;
			public String value;
			public int qd_size;
			public qdListTeacher(qiandao_table p){
				this.fb_time=p.getFbtime();
				this.jz_time=p.getJztime();
				this.name=p.getName();
				this.way=p.getWay();
				this.value=p.getCode();
			}
		}
		
		public static class qdInfoTeacher{
			public String stu_phone;
			public String stu_name;
			public String stu_id;
			public String qd_time;
			public String value;
			public qdInfoTeacher(qiandao_log_table p) {
				this.stu_phone=p.getStuphone();
				this.qd_time=p.getQdtime();
				this.value=p.getValue();
			}
		}
		public static class qdListStu{
			public String fb_time;
			public String jz_time;
			public String name;
			public String way;
			public String value;
			public qdListStu(qiandao_table p) {
				this.fb_time=p.getFbtime();
				this.jz_time=p.getJztime();
				this.name=p.getName();
				this.way=p.getWay();
				this.value=p.getCode();
			}
		}
		public static class courseidPara{
			public String course_id;
		}
		public static class fbqdPara{
			public String course_id;
			public String name;
			public String way;
			public long jz_long;
		}
		public static class courseidTimePara{
			public String course_id;
			public String fb_time;
		}
		public static class courseidPhonePara{
			public String student_phone;
			public String course_id;
		}
		public static class changePara{
			public String course_id;
			public String fb_time;
			public String student_phone;
			public String value;
		}
		public static class qdPara{
			public String student_phone;
			public String course_id;
			public String fb_time;
			public String code;
		}
		public static class resetTimePara{
			public String course_id;
			public String fb_time;
			public long jz_long;
		}
	}
}
