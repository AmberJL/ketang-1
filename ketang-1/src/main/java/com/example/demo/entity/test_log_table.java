package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.course_time_phone_key;

//学生提交测试记录表Entity
@Entity
@Table(name = "test_log_table")
@IdClass(course_time_phone_key.class)
public class test_log_table {
	
	//联合主键
	@EmbeddedId
	private course_time_phone_key key;
	
	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
	
	//发布时间
	@Id
	@Column(name = "fb_time")
	private String fbtime;
	
	//学生手机号
	@Id
	@Column(name = "stu_phone")
	private String stuphone;

	//提交时间
	@Column(name = "tj_time")
	private String tjtime;

	
	
	public course_time_phone_key getKey() {
		return key;
	}

	public void setKey(course_time_phone_key key) {
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

	public String getTjtime() {
		return tjtime;
	}

	public void setTjtime(String tjtime) {
		this.tjtime = tjtime;
	}
}
