package com.example.demo.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.homework_file_table;
import com.example.demo.entity.homework_log_table;
import com.example.demo.entity.homework_table;

public interface HomeworkService {
	
	//添加作业
	public String add(List<MultipartFile> files,String course_id,String title,String value,long jz_long);
	
	//修改作业
	public String update(String course_id,String fb_time,String title,String value,long jz_long);
	
	//获取作业列表
	public List<homework_table> getList(String course_id);
	
	//删除作业
	public String remove(String course_id,String fb_time);
	
	//获取作业详情
	public homework_table getInfo(String course_id,String fb_time);
	
	//获取作业附件列表
	public List<homework_file_table> getInfoFileList(String course_id,String fb_time);
	
	//获取提交详情
	public List getLog(String course_id,String fb_time);
	
	//获取学生提交的文件列表
	public List<homework_log_table> getHomework(String course_id,String fb_time,String stu_phone);
	
	//提交作业
	public String put(List<MultipartFile> files,String course_id,String fb_time,String stu_phone);
	
	//学生删除自己的文件
	public String removeLog(String course_id,String fb_time,String stu_phone,String file_id);

	//获取文件名
	public String getName(String course_id, String file_id, String mode);

	//获取提交人数
	int getLogSize(String course_id, String fb_time);
}
