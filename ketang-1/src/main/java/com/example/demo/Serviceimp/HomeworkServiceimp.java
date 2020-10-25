package com.example.demo.Serviceimp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.HomeworkService;
import com.example.demo.entity.course_log_table;
import com.example.demo.entity.homework_file_table;
import com.example.demo.entity.homework_log_table;
import com.example.demo.entity.homework_table;
import com.example.demo.entity.student_table;
import com.example.demo.entity.key.course_time_key;
import com.example.demo.repository.CourseLogRepositpry;
import com.example.demo.repository.HomeworkFileRespository;
import com.example.demo.repository.HomeworkLogRespository;
import com.example.demo.repository.HomeworkRespository;
import com.example.demo.repository.StudentRespository;
import com.example.demo.tool.Time;

@Service
public class HomeworkServiceimp implements HomeworkService {
	
	private final HomeworkRespository dao;
	private final HomeworkLogRespository logDao;
	private final HomeworkFileRespository fileDao;
	private final CourseLogRepositpry cLogDao;
	private final StudentRespository stuDao;
	
	public HomeworkServiceimp (@Autowired HomeworkRespository dao,@Autowired HomeworkLogRespository logDao,@Autowired HomeworkFileRespository fileDao,@Autowired CourseLogRepositpry cLogDao,@Autowired StudentRespository stuDao) {
		this.cLogDao = cLogDao;
		this.fileDao = fileDao;
		this.dao = dao;
		this.logDao = logDao;
		this.stuDao = stuDao;
		
	}

	//添加作业
	@Override
	public String add(List<MultipartFile> files, String course_id, String title, String value,long jz_long) {
		int failedtimes=0;
		String fb_time=Time.getTime();
		String jz_time=Time.getTimeNext(jz_long);
		for(int i=0;i<files.size();i++) {
			String name=FileServiceTools.saveFile(files.get(i), course_id);
			if("保存失败".equals(name)) {
				failedtimes++;
				i--;
				if(failedtimes>10)return "错误";
				continue;
			}
			homework_file_table data=new homework_file_table();
			data.setCourseid(course_id);
			data.setFbtime(fb_time);
			data.setFilename(files.get(i).getOriginalFilename());
			data.setFilepath(name);
			fileDao.save(data);
		}
		homework_table homework=new homework_table();
		homework.setCourseid(course_id);
		homework.setFbtime(fb_time);
		homework.setTitle(title);
		homework.setValue(value);
		homework.setJztime(jz_time);
		dao.save(homework);
		return "添加成功";
	}

	//获取作业列表
	@Override
	public List<homework_table> getList(String course_id) {
		return dao.findAllByCourseidOrderByFbtimeDesc(course_id);
	}

	//删除作业
	@Override
	public String remove(String course_id, String fb_time) {
		List<homework_file_table> file1;
		List<homework_log_table> file2;
		try{
			dao.deleteById(new course_time_key(course_id,fb_time));
			file1=fileDao.findAllByCourseidAndFbtime(course_id, fb_time);
			fileDao.deleteAllByCourseidAndFbtime(course_id, fb_time);
			file2=logDao.findAllByCourseidAndFbtime(course_id, fb_time);
			logDao.deleteAllByCourseidAndFbtime(course_id, fb_time);
			
			//删除文件
			/*
			for(int i=0;i<file1.size();i++) {
				FileServiceTools.delete(course_id,file1.get(i).getFilepath());
			}
			for(int i=0;i<file2.size();i++) {
				FileServiceTools.delete(course_id,file2.get(i).getFilepath());
			}
			*/
			
			return "删除成功";
		}catch(Exception e) {
			return "删除出错";
		}

	}

	//获取作业详情
	@Override
	public homework_table getInfo(String course_id, String fb_time) {
		try {
			return dao.findById(new course_time_key(course_id,fb_time)).get();
		}catch(Exception e) {
			return new homework_table();
		}
	}
	
	//获取作业附件列表
	@Override
	public List<homework_file_table> getInfoFileList(String course_id,String fb_time){
		return fileDao.findAllByCourseidAndFbtimeOrderByFbtimeDesc(course_id, fb_time);
	}

	//获取提交详情
	@Override
	public List<Map<String,String>> getLog(String course_id, String fb_time) {
		List<Map<String, String>> log=new ArrayList<Map<String, String>>();
		List<course_log_table> students=getStudents(course_id);
		for(int i=0;i<students.size();i++) {
			Map<String, String> temp=new HashMap<String, String>();
			String phone=students.get(i).getStudentphone();
			temp.put("stu_phone",phone);
			if(logDao.countByCourseidAndFbtimeAndStuphone(course_id, fb_time, phone)>0)
				temp.put("value","已提交");
			else
				temp.put("value","未提交");
			
			try {
				student_table stu=stuDao.findByPhone(phone);
				temp.put("name", stu.getStuname());
				temp.put("stu_id", stu.getStuid());
			}catch(Exception e) {
				e.printStackTrace();
				temp.put("name", null);
				temp.put("stu_id", null);
			}
			
			
			log.add(temp);
		}
		return log;
	}
	
	//获取提交作业人数
	@Override
	public int getLogSize(String course_id, String fb_time) {
		int size=0;
		List<course_log_table> students=getStudents(course_id);
		for(int i=0;i<students.size();i++) {
			if(logDao.countByCourseidAndFbtimeAndStuphone(course_id, fb_time, students.get(i).getStudentphone())>0)size++;
		}
		return size;
	}
	
	private List<course_log_table> getStudents(String course_id){
		return cLogDao.findAllByCourseid(course_id);
	}

	//获取学生提交的文件列表
	@Override
	public List<homework_log_table> getHomework(String course_id, String fb_time, String stu_phone) {
		return logDao.findAllByCourseidAndFbtimeAndStuphone(course_id, fb_time, stu_phone);
	}

	//提交作业
	@Override
	public String put(List<MultipartFile> files, String course_id, String fb_time, String stu_phone) {
		
		homework_table hw;
		
		try {
			hw=dao.findById(new course_time_key(course_id,fb_time)).get();
		}catch(Exception e) {
			return "作业不存在";
		}
		
		String time=Time.getTime();
		
		if(Time.getDate(hw.getJztime()).getTime() < Time.getDate(time).getTime()) 
			return "已截止";
		
		if(files.size()==0)return "无文件";
		
		int failedtimes=0;
		for(int i=0;i<files.size();i++) {
			String name=FileServiceTools.saveFile(files.get(i), course_id);
			if("保存失败".equals(name)) {
				failedtimes++;
				i--;
				if(failedtimes>10)return "错误";
				continue;
			}
			homework_log_table data=new homework_log_table();
			data.setCourseid(course_id);
			data.setFbtime(fb_time);
			data.setTjtime(time);
			data.setStuphone(stu_phone);
			data.setFilename(files.get(i).getOriginalFilename());
			data.setFilepath(name);
			logDao.save(data);
		}
		
		return "上传成功";
	}
	
	//获取文件名
	@Override
	public String getName(String course_id,String file_id,String mode) {
		try {
			if("stu".equals(mode))return logDao.findByCourseidAndFilepath(course_id, file_id).getFilename();
			else if("tea".equals(mode))return fileDao.findByCourseidAndFilepath(course_id, file_id).getFilename();
			else return null;
		}catch(Exception e) {
			return null;
		}
	}

}
