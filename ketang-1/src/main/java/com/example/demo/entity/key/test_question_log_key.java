package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//学生提交测试答案表主键
@Embeddable
public class test_question_log_key implements Serializable{

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
	
	//学生手机号
	@Id
	@Column(name = "stu_phone",insertable=false,updatable=false)
	private String stuphone;
	
	public test_question_log_key() {}
	
	public test_question_log_key(String courseid,String fbtime,int tihao,String stuphone) {
		this.courseid=courseid;
		this.fbtime=fbtime;
		this.tihao=tihao;
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

	public int getTihao() {
		return tihao;
	}

	public void setTihao(int tihao) {
		this.tihao = tihao;
	}

	public String getStuphone() {
		return stuphone;
	}

	public void setStuphone(String stuphone) {
		this.stuphone = stuphone;
	}
}
