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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.SharedfileService;
import com.example.demo.Serviceimp.FileServiceTools;
import com.example.demo.Serviceimp.SharedfileServiceimp;
import com.example.demo.entity.sharedfile_table;

@RestController
@RequestMapping("/sharedfile")
public class SharedfileController {
	
	private final SharedfileService sharedfileService;
	
	public SharedfileController(SharedfileServiceimp sharedfileService) {
		this.sharedfileService=sharedfileService;
	}
	
	//获取列表
	@RequestMapping("/getList")
	public List<?> getList(@RequestBody pack.Para p){
		
		String course_id=p.course_id;
		
		return pack.parseFileInfo(sharedfileService.getList(course_id));
	}
	
	//上传
	@RequestMapping("/upload")
	public String fileUpload(@RequestParam("file") List<MultipartFile> files,@RequestParam Map<String,String> param) {
		
		String course_id=param.get("course_id");
		
		return sharedfileService.upload(files,course_id);
	}
	
	//下载
	@RequestMapping("/download")
	public void fileDownload(HttpServletResponse response,HttpServletRequest request) {
		
		String course_id=request.getParameter("course_id");
		String file_id=request.getParameter("file_id");
		
		File file=FileServiceTools.getFile(course_id, file_id);
		if(file==null)return;
		
		String file_name=sharedfileService.getName(course_id,file_id);
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
	
	//删除文件
	@RequestMapping("/remove")
	public String remove(@RequestBody pack.Para p) {
		
		String course_id=p.course_id;
		String file_id=p.file_id;
		
		return sharedfileService.remove(course_id,file_id);
	}
	
	
	
	
	//包装参数和返回值
	private static class pack{
		public static List<fileInfo> parseFileInfo(List<sharedfile_table> p){
			List<fileInfo> temp=new ArrayList();
			for(int i=0;i<p.size();i++) {
				temp.add(new fileInfo(p.get(i)));
			}
			return temp;
		}
		public static class fileInfo{
			public String file_id;
			public String file_name;
			public String fb_time;
			public fileInfo(sharedfile_table p) {
				this.file_id=p.getFilepath();
				this.file_name=p.getFilename();
				this.fb_time=p.getFbtime();
			}
		}
		public static class Para{
			public String course_id;
			public String file_id;
		}
	}
}
