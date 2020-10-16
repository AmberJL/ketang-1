package com.example.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

//共享文件表主键
@Embeddable
public class sharedfile_key implements Serializable{

	private static final long serialVersionUID = 1L;

	//课程id
	@Id
	@Column(name = "course_id",insertable=false,updatable=false)
	private String courseid;
	
	//文件路径
	@Id
	@Column(name = "file_path",insertable=false,updatable=false)
	private String filepath;

	public sharedfile_key() {}
	
	public sharedfile_key(String courseid,String filepath) {
		this.courseid=courseid;
		this.filepath=filepath;
	}
	
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
