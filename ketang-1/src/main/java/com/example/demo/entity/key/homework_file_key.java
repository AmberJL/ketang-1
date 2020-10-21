package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//发布作业附件表主键
@Embeddable
public class homework_file_key implements Serializable {

	private static final long serialVersionUID = 1L;

	//课程id
	@Column(name = "course_id",insertable=false,updatable=false)
	private String courseid;
	
	//发布时间
	@Column(name = "fb_time",insertable=false,updatable=false)
	private String fbtime;
	
	//服务器文件路径
	@Column(name = "file_path",insertable=false,updatable=false)
	private String filepath;
	
	public homework_file_key() {}

	public homework_file_key(String courseid, String fbtime, String filepath) {
		this.courseid = courseid;
		this.fbtime = fbtime;
		this.filepath = filepath;
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
