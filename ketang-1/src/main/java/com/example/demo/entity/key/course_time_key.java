package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//课程id 和 发布时间 作为联合主键
@Embeddable
public class course_time_key implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//课程id
	@Id
	@Column(name = "course_id",insertable=false,updatable=false)
	private String courseid;
	
	//发布时间
	@Id
	@Column(name = "fb_time",insertable=false,updatable=false)
	private String fbtime;

	
	
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
	
	
}
