package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.sharedfile_key;

//共享文件表Entity
@Entity
@Table(name = "sharedfile_table")
@IdClass(sharedfile_key.class)
public class sharedfile_table {
	
	//联合主键
	@EmbeddedId
	private sharedfile_key key;
	
	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
	
	//文件路径
	@Id
	@Column(name = "file_path")
	private String filepath;
	
	//发布时间
	@Column(name = "fb_time")
	private String fbtime;
	
	//文件名称
	@Column(name = "file_name")
	private String filename;

	
	
	public sharedfile_key getKey() {
		return key;
	}

	public void setKey(sharedfile_key key) {
		this.key = key;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFbtime() {
		return fbtime;
	}

	public void setFbtime(String fbtime) {
		this.fbtime = fbtime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
