package com.example.demo.Serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.NoticeService;
import com.example.demo.entity.notice_table;
import com.example.demo.entity.key.course_time_key;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.NoticeRespository;
import com.example.demo.tool.Time;

@Service
public class NoticeServiceimp implements NoticeService {
	
	private final NoticeRespository dao;
	private final CourseRepository courseDao;
	
	public NoticeServiceimp(@Autowired NoticeRespository dao,@Autowired CourseRepository courseDao) {
		this.dao=dao;
		this.courseDao=courseDao;
	}

	//老师添加公告
	@Override
	public String add(String course_id, String value, String title) {
		
		try {
			courseDao.findById(course_id).get();
		}catch(Exception e) {
			return "2";//课程不存在
		}
		
		
		notice_table temp=new notice_table();
		temp.setCourseid(course_id);
		temp.setFbtime(Time.getTime());
		temp.setValue(value);
		temp.setTitle(title);
		try {
			dao.save(temp);
			return "1";//发布成功
		}catch(Exception e) {
			return "0";//发布失败
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
			return "1";//删除成功
		}catch(Exception e) {
			return "0";//删除失败
		}
	}
	
	//修改公告
	@Override
	public String change(String course_id, String fb_time, String value, String title) {
		try {
			notice_table temp=dao.findById(new course_time_key(course_id,fb_time)).get();
			temp.setTitle(title);
			temp.setValue(value);
			temp.setFbtime(Time.getTime());
			dao.deleteById(new course_time_key(course_id,fb_time));
			dao.save(temp);
			return "1";//修改成功
		}catch(Exception e) {
			return "0";//修改失败
		}
	}

}
