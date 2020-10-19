package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//测试题目表主键
@Embeddable
public class test_question_key implements Serializable{

	private static final long serialVersionUID = 1L;

	//课程id
	@Id
	@Column(name = "course_id",insertable=false,updatable=false)
	private String courseid;
	
	//发布时间
	@Id
	@Column(name = "fb_time",insertable=false,updatable=false)
	private String fbtime;
	
	//题号
	@Id
	@Column(name = "ti_hao",insertable=false,updatable=false)
	private int tihao;

	public test_question_key() {}
	
	public test_question_key(String courseid,String fbtime,int tihao) {
		this.courseid=courseid;
		this.fbtime=fbtime;
		this.tihao=tihao;
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

	public int getTihao() {
		return tihao;
	}

	public void setTihao(int tihao) {
		this.tihao = tihao;
	}
}
