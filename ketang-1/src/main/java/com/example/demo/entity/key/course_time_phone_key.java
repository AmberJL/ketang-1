package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//课程id 发布时间 学生电话 作为联合主键
@Embeddable
public class course_time_phone_key implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//课程id
	@Id
	@Column(name = "course_id",insertable=false,updatable=false)
	private String courseid;
	
	//发布时间
	@Id
	@Column(name = "fb_time",insertable=false,updatable=false)
	private String fbtime;
	
	//学生手机号
	@Id
	@Column(name = "stu_phone",insertable=false,updatable=false)
	private String stuphone;
	
	public course_time_phone_key() {}
	
	public course_time_phone_key(String courseid,String fbtime,String stuphone) {
		this.courseid=courseid;
		this.fbtime=fbtime;
		this.stuphone=stuphone;
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
}
