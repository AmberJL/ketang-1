package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.course_time_key;

//作业发布表Entity
@Entity
@Table(name = "homework_table")
@IdClass(course_time_key.class)
public class homework_table {
	
	//联合主键
	@EmbeddedId
	private course_time_key key;
	
	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
		
	//作业发布时间
	@Id
	@Column(name = "fb_time")
	private String fbtime;
	
	//作业截止时间
	@Column(name = "jz_time")
	private String jztime;
	
	//作业题目
	@Column(name = "value")
	private String value;
	
	//作业标题
	@Column(name = "title")
	private String title;

	
	
	public course_time_key getKey() {
		return key;
	}

	public void setKey(course_time_key key) {
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

	public String getJztime() {
		return jztime;
	}

	public void setJztime(String jztime) {
		this.jztime = jztime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
