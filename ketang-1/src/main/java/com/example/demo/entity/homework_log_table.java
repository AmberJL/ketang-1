package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.homework_log_key;

//作业文件提交记录表Entity
@Entity
@Table(name = "homework_log_table")
@IdClass(homework_log_key.class)
public class homework_log_table {
	
	//联合主键
	@EmbeddedId
	private homework_log_key key;
	
	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
	
	//作业发布时间
	@Id
	@Column(name = "fb_time")
	private String fbtime;
	
	//学生手机号
	@Id
	@Column(name = "stu_phone")
	private String stuphone;
	
	//服务器文件路径
	@Id
	@Column(name = "file_path")
	private String filepath;
	
	//作业提交时间
	@Column(name = "tj_time")
	private String tjtime;
	
	//文件名
	@Column(name = "file_name")
	private String filename;

	
	
	public homework_log_key getKey() {
		return key;
	}

	public void setKey(homework_log_key key) {
		this.key = key;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getFbtime() {
		return fbtime;
	}

	public void setFbtime(String fbtime) {
		this.fbtime = fbtime;
	}

	public String getStuphone() {
		return stuphone;
	}

	public void setStuphone(String stuphone) {
		this.stuphone = stuphone;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getTjtime() {
		return tjtime;
	}

	public void setTjtime(String tjtime) {
		this.tjtime = tjtime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
