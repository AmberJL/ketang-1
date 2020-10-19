package com.example.demo.Service;

import java.util.List;

import com.example.demo.entity.notice_table;

public interface NoticeService {
	//老师添加公告
	public String add(String course_id,String value);
	
	//获取公告列表
	public List<notice_table> get(String course_id);

	//删除公告
	public String remove(String course_id,String fb_time);
}
