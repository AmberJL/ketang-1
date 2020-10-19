package com.example.demo.Service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.sharedfile_table;

public interface SharedfileService {
	
	//获取列表
	public List<sharedfile_table> getList(String course_id);
	
	//上传
	public String upload(List<MultipartFile> files,String course_id);
	
	//下载
	public String download(String course_id,String file_id);
	
	//获取文件名
	public String getName(String course_id,String file_id);
}
