package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.homework_file_key;

//发布作业附件表Entity
@Entity
@Table(name = "homework_file_table")
@IdClass(homework_file_key.class)
public class homework_file_table {

	//联合主键
	@EmbeddedId
	private homework_file_key key;
	
	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
		
	//作业发布时间
	@Id
	@Column(name = "fb_time")
	private String fbtime;
	
	//服务器文件路径
	@Id
	@Column(name = "file_path")
	private String filepath;
	
	//文件名称
	@Column(name = "file_name")
	private String filename;
	
	//文件大小
	@Column(name = "file_size")
	private String filesize;

	public homework_file_key getKey() {
		return key;
	}

	public void setKey(homework_file_key key) {
		this.key = key;
	}

	public String getFbtime() {
		return fbtime;
	}

	public void setFbtime(String fbtime) {
		this.fbtime = fbtime;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	
}
