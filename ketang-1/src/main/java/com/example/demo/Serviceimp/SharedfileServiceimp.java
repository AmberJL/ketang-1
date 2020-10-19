package com.example.demo.Serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.SharedfileService;
import com.example.demo.entity.sharedfile_table;
import com.example.demo.entity.key.sharedfile_key;
import com.example.demo.repository.SharedfileRespository;
import com.example.demo.tool.Time;

@Service
public class SharedfileServiceimp implements SharedfileService{

	private final SharedfileRespository dao;
	
	public SharedfileServiceimp(@Autowired SharedfileRespository dao) {
		this.dao=dao;
	}
	
	//获取列表
	@Override
	public List<sharedfile_table> getList(String course_id) {
		return dao.findAllByCourseidOrderByFbtimeDesc(course_id);
	}

	//上传
	@Override
	public String upload(List<MultipartFile> files,String course_id) {
		int failedtimes=0;
		for(int i=0;i<files.size();i++) {
			String name=FileServiceTools.saveFile(files.get(i), course_id);
			if("保存失败".equals(name)) {
				failedtimes++;
				i--;
				if(failedtimes>10)return "错误";
				continue;
			}
			sharedfile_table data=new sharedfile_table();
			data.setCourseid(course_id);
			data.setFbtime(Time.getTime());
			data.setFilename(files.get(i).getOriginalFilename());
			data.setFilepath(name);
			dao.save(data);
		}
		return "上传成功";
	}

	//下载
	@Override
	public String download(String course_id, String file_id) {
		// TODO Auto-generated method stub
		return null;
	}

	//获取文件名
	@Override
	public String getName(String course_id, String file_id) {
		try {
			return dao.findById(new sharedfile_key(course_id,file_id)).get().getFilename();
		}catch(Exception e) {
			return null;
		}
	}

}
