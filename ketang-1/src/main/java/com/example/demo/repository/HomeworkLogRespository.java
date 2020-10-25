package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.homework_log_table;
import com.example.demo.entity.key.homework_log_key;

public interface HomeworkLogRespository extends JpaRepository<homework_log_table,homework_log_key>{
	
	public List<homework_log_table> findAllByCourseidAndFbtimeAndStuphone(String course_id,String fb_time,String stu_phone);
	
	public List<homework_log_table> findAllByCourseidAndFbtime(String course_id,String fb_time);
	
	public boolean deleteAllByCourseidAndFbtime(String course_id,String fb_time);
	
	public homework_log_table findByCourseidAndFilepath(String course_id,String file_path);
	
	public int countByCourseidAndFbtimeAndStuphone(String course_id,String fb_time,String stu_phone);

}
