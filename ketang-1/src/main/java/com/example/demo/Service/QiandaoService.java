package com.example.demo.Service;

import java.util.List;

import com.example.demo.entity.qiandao_log_table;
import com.example.demo.entity.qiandao_table;

public interface QiandaoService {
	//老师获取签到列表
	public List<qiandao_table> getListT(String course_id);
	
	//老师发布签到
	public String newQd(String course_id,String name,String way,long jz_long);
	
	////老师获取单次签到人数
	public String getSize(String course_id, String fb_time);
	
	//老师获取单次签到详情
	public List<qiandao_log_table> getValue(String course_id,String fb_time);
	
	//老师修改签到状态
	public String change(String course_id,String fb_time,String student_phone,String value);
	
	//老师删除签到
	public String remove(String course_id,String fb_time);
	
	//学生获取签到列表
	public List<qiandao_table> getListS(String student_phone, String course_id);
	
	//学生签到
	public String qd(String student_phone,String course_id,String fb_time,String code);

	//老师重置签到时间
	public String resetTime(String course_id,String fb_time,long jz_long);
}
