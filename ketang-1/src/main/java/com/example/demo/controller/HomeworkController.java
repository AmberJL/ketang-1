package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

import lombok.Data;

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
	
	//修改作业
	@RequestMapping("/update")
	public String update(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		String title=p.title;
		String value=p.value;
		long jz_long=p.jz_long;
		
		return homeworkService.update(course_id, fb_time, title, value, jz_long);
	}
	
	//获取作业列表
	@RequestMapping("/getList")
	public List<?> getList(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String stu_phone=p.stu_phone;
		
		boolean isTea;
		if(stu_phone==null)isTea=true;
		else isTea=false;
		
		List<pack.homework> temp=pack.parseHomework(homeworkService.getList(course_id));
		for(int i=0;i<temp.size();i++) {
			pack.homework hw=temp.get(i);
			hw.file_count=homeworkService.getFileCount(course_id, hw.fb_time)+"";
			if(isTea)
				hw.commit=homeworkService.getLogSize(course_id, hw.fb_time);
			else
				hw.commit=homeworkService.isLogged(course_id, hw.fb_time, stu_phone);
		}
		return temp;
	}
	
	//删除作业
	@RequestMapping("/remove")
	public String remove(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return homeworkService.remove(course_id, fb_time);
	}
	
	//获取作业详情
	@RequestMapping("/getInfo")
	public Object getInfo(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return new pack.homework(homeworkService.getInfo(course_id, fb_time));
	}
	
	//获取作业附件列表
	@RequestMapping("/getInfoFileList")
	public List<?> getInfoFileList(@RequestBody pack.para p){
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return pack.parseFileInfoTea(homeworkService.getInfoFileList(course_id, fb_time));
	}
	
	//获取提交详情
	@RequestMapping("/getLog")
	public List<?> getLog(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		
		return pack.parseStuInfo(homeworkService.getLog(course_id, fb_time));
	}
	
	//获取学生提交的文件列表
	@RequestMapping("/getHomework")
	public List<?> getHomework(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		String stu_phone=p.stu_phone;
		
		return pack.parseFileInfoStu(homeworkService.getHomework(course_id, fb_time, stu_phone));
	}
	
	//提交作业
	@RequestMapping("/put")
	public String put(@RequestParam("file") List<MultipartFile> files,@RequestParam Map<String,String> param) {
		
		String course_id=param.get("course_id");
		String fb_time=param.get("fb_time");
		String stu_phone=param.get("stu_phone");
		
		return homeworkService.put(files, course_id, fb_time, stu_phone);
	}
	
	//学生删除作业文件
	@RequestMapping("/removeLog")
	public String removeLog(@RequestBody pack.para p) {
		
		String course_id=p.course_id;
		String fb_time=p.fb_time;
		String stu_phone=p.stu_phone;
		String file_id=p.file_id;
		
		return homeworkService.removeLog(course_id, fb_time, stu_phone, file_id);
		
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

	//包装参数和返回值
	private static class pack{
		public static List<stuLogInfo> parseStuInfo(List<Map<String,String>> p){
			List<stuLogInfo> temp=new ArrayList<stuLogInfo>();
			for(int i=0;i<p.size();i++) {
				temp.add(new stuLogInfo(p.get(i)));
			}
			return temp;
		}
		public static List<fileInfo> parseFileInfoTea(List<homework_file_table> p){
			List<fileInfo> temp=new ArrayList<fileInfo>();
			for(int i=0;i<p.size();i++) {
				temp.add(new fileInfo(p.get(i)));
			}
			return temp;
		}
		public static List<fileInfo> parseFileInfoStu(List<homework_log_table> p){
			List<fileInfo> temp=new ArrayList<fileInfo>();
			for(int i=0;i<p.size();i++) {
				temp.add(new fileInfo(p.get(i)));
			}
			return temp;
		}
		public static List<homework> parseHomework(List<homework_table> p){
			List<homework> temp=new ArrayList<homework>();
			for(int i=0;i<p.size();i++) {
				temp.add(new homework(p.get(i)));
			}
			return temp;
		}
		@Data
		public static class homework{
			String fb_time;
			String jz_time;
			String value;
			String title;
			String file_count;
			String commit;
			public homework(homework_table p) {
				this.fb_time=p.getFbtime();
				this.jz_time=p.getJztime();
				this.value=p.getValue();
				this.title=p.getTitle();
			}
		}
		@Data
		public static class fileInfo{
			String course_id;
			String fb_time;
			String file_id;
			String file_name;
			String file_size;
			String mode;
			public fileInfo(homework_file_table p) {
				this.course_id=p.getCourseid();
				this.fb_time=p.getFbtime();
				this.file_id=p.getFilepath();
				this.file_name=p.getFilename();
				this.file_size=p.getFilesize();
				this.mode="tea";
			}
			public fileInfo(homework_log_table p) {
				this.course_id=p.getCourseid();
				this.fb_time=p.getFbtime();
				this.file_id=p.getFilepath();
				this.file_name=p.getFilename();
				this.file_size=p.getFilesize();
				this.mode="stu";
			}
		}
		@Data
		public static class stuLogInfo{
			String stu_id;
			String stu_phone;
			String stu_name;
			String value;
			String file_count;
			public stuLogInfo(Map<String,String> p) {
				this.stu_id=p.get("stu_id");
				this.stu_name=p.get("name");
				this.stu_phone=p.get("stu_phone");
				this.value=p.get("value");
				this.file_count=p.get("count");
			}
		}
		@Data
		public static class para{
			String course_id;
			String fb_time;
			String stu_phone;
			String file_id;
			String title;
			String value;
			long jz_long;
		}
	}
}
