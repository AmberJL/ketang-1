package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.HomeworkService;
import com.example.demo.Serviceimp.FileServiceTools;
import com.example.demo.Serviceimp.HomeworkServiceimp;
import com.example.demo.entity.homework_file_table;
import com.example.demo.entity.homework_log_table;
import com.example.demo.entity.homework_table;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
	
	private final HomeworkService homeworkService;
	
	public HomeworkController(@Autowired HomeworkServiceimp homeworkService) {
		this.homeworkService=homeworkService;
	}
	
	//添加作业
	@RequestMapping("/add")
	public String add(@RequestParam("file") List<MultipartFile> files,@RequestParam Map<String,String> param) {
		
		String course_id=param.get("course_id");
		String title=param.get("title");
		String value=param.get("value");
		long jz_long=Long.parseLong(param.get("jz_long"));
		
		return homeworkService.add(files, course_id, title, value, jz_long);
	}
	
	//获取作业列表
	@RequestMapping("/getList")
	public List<homework_table> getList(String course_id) {
		return homeworkService.getList(course_id);
	}
	
	//删除作业
	@RequestMapping("/remove")
	public String remove(String course_id, String fb_time) {
		return homeworkService.remove(course_id, fb_time);
	}
	
	//获取作业详情
	@RequestMapping("/getInfo")
	public homework_table getInfo(String course_id, String fb_time) {
		return homeworkService.getInfo(course_id, fb_time);
	}
	
	//获取作业附件列表
	@RequestMapping("/getInfoFileList")
	public List<homework_file_table> getInfoFileList(String course_id,String fb_time){
		return homeworkService.getInfoFileList(course_id, fb_time);
	}
	
	//获取提交详情
	public List getLog(String course_id, String fb_time) {
		return homeworkService.getLog(course_id, fb_time);
	}
	
	//获取学生提交的文件列表
	@RequestMapping("/getHomework")
	public List<homework_log_table> getHomework(String course_id, String fb_time, String stu_phone) {
		return homeworkService.getHomework(course_id, fb_time, stu_phone);
	}
	
	//提交作业
	@RequestMapping("/put")
	public String put(@RequestParam("file") List<MultipartFile> files,@RequestParam Map<String,String> param) {
		
		String course_id=param.get("course_id");
		String fb_time=param.get("fb_time");
		String stu_phone=param.get("stu_phone");
		
		return homeworkService.put(files, course_id, fb_time, stu_phone);
	}
	
	//下载
	@RequestMapping("/download")
	public void fileDownload(HttpServletResponse response,HttpServletRequest request) {
		
		String course_id=request.getParameter("course_id");
		String file_id=request.getParameter("file_id");
		String mode=request.getParameter("mode");
		
		File file=FileServiceTools.getFile(course_id, file_id);
		if(file==null)return;
		
		String file_name=homeworkService.getName(course_id, file_id, mode);
		if(file_name==null)return;
		
		
		//发送文件
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			response.setHeader("Content-Disposition","attachment;fileName="+java.net.URLEncoder.encode(file_name,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {}
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while(i != -1){
                os.write(buffer);
                i = bis.read(buffer);
            }
        } catch (Exception e) {}
        try {
            bis.close();
            fis.close();
        } catch (IOException e) {}
	}

}
