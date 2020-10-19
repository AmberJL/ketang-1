package com.example.demo.Serviceimp;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileServiceTools {
	
	//服务器本地文件路径根文件夹
	private static final String rootPath="D:\\米课堂文件测试";
	
	public static String saveFile(MultipartFile mfile,String course_id) {
		
		pack.file temp=newFile(course_id);
		
		try {
			mfile.transferTo(temp.file);
		} catch (Exception e) {
			return "保存失败";
		}
		return temp.name;
	}
	
	public static File getFile(String course_id,String file_id) {
		
		File temp=new File(rootPath+"\\"+course_id+"\\"+file_id);
		
		if(temp.exists()&&!temp.isDirectory()) {
			return temp;
		}else
			return null;
	}
	
	public static void delete(String course_id,String file_id) {
		
		File temp=new File(rootPath+"\\"+course_id+"\\"+file_id);
		
		if(temp.exists()&&!temp.isDirectory()) {
			temp.delete();
		}
		
	}
	
	private static void checkDir(String course_id) {
		File file=new File(rootPath+"\\"+course_id);
		if(file.exists()&&file.isDirectory())return;
		else file.mkdirs();
	}
	private static pack.file newFile(String course_id) {
		checkDir(course_id);
		for(;;) {
			String fileName=new Date().getTime()+"";
			File file=new File(rootPath+"\\"+course_id+"\\"+fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				continue;
			}
			return new pack.file(file,fileName);
		}
	}
	private static class pack{
		public static class file{
			public File file;
			public String name;
			public file(File file,String name) {
				this.file=file;
				this.name=name;
			}
		}
	}
}
