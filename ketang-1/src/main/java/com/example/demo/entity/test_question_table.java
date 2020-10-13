package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.key.test_question_key;

//测试题目表Entity
@Entity
@Table(name = "test_question_table")
@IdClass(test_question_key.class)
public class test_question_table {
	
	//联合主键
	@EmbeddedId
	private test_question_key key;

	//课程id
	@Id
	@Column(name = "course_id")
	private String courseid;
	
	//发布时间
	@Id
	@Column(name = "fb_time")
	private String fbtime;
	
	//题号
	@Id
	@Column(name = "ti_hao")
	private String tihao;
	
	//题目
	@Column(name = "value")
	private String value;

	
	
	public test_question_key getKey() {
		return key;
	}

	public void setKey(test_question_key key) {
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

	public String getTihao() {
		return tihao;
	}

	public void setTihao(String tihao) {
		this.tihao = tihao;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
