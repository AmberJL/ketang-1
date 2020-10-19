package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//作业提交文件记录表主键
@Embeddable
public class homework_log_key implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//课程id
	@Column(name = "course_id",insertable=false,updatable=false)
	private String courseid;
	
	//发布时间
	@Column(name = "fb_time",insertable=false,updatable=false)
	private String fbtime;
	
	//学生手机号
	@Column(name = "stu_phone",insertable=false,updatable=false)
	private String stuphone;
	
	//服务器文件路径
	@Column(name = "file_path",insertable=false,updatable=false)
	private String filepath;

	public homework_log_key() {}
	
	public homework_log_key(String courseid,String fbtime,String stuphone,String filepath) {
		this.courseid=courseid;
		this.fbtime=fbtime;
		this.stuphone=stuphone;
		this.filepath=filepath;
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
}
