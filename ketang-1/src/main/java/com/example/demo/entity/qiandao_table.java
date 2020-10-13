package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.course_time_key;

//老师发布签到表Entity
@Entity
@Table(name = "qiandao_table")
@IdClass(course_time_key.class)
public class qiandao_table {
	
	//联合主键
	@EmbeddedId
	private course_time_key key;
	
	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
	
	//签到发布时间
	@Id
	@Column(name = "fb_time")
	private String fbtime;
	
	//签到截止时间
	@Column(name = "jz_time")
	private String jztime;

	//签到名称
	@Column(name = "name")
	private String name;
	
	//签到方式
	@Column(name = "way")
	private String way;
	
	//签到码
	@Column(name = "code")
	private String code;

	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
