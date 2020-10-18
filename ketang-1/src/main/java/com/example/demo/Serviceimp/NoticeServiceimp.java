package com.example.demo.Serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.NoticeService;
import com.example.demo.entity.notice_table;
import com.example.demo.entity.key.course_time_key;
import com.example.demo.repository.NoticeRespository;
import com.example.demo.tool.Time;

@Service
public class NoticeServiceimp implements NoticeService {
	
	private final NoticeRespository dao;
	
	public NoticeServiceimp(@Autowired NoticeRespository dao) {
		this.dao=dao;
	}

	//老师添加公告
	@Override
	public String add(String course_id, String value) {
		notice_table temp=new notice_table();
		temp.setCourseid(course_id);
		temp.setFbtime(Time.getTime());
		temp.setValue(value);
		try {
			dao.save(temp);
			return "发布成功";
		}catch(Exception e) {
			return "发布失败";
		}
	}

	//获取公告列表
	@Override
	public List<notice_table> get(String course_id) {
		return dao.findAllByCourseidOrderByFbtimeDesc(course_id);
	}

	//删除公告
	@Override
	public String remove(String course_id, String fb_time) {
		try {
			dao.deleteById(new course_time_key(course_id,fb_time));
			return "删除成功";
		}catch(Exception e) {
			return "删除失败";
		}
	}

}
