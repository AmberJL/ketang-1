package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.homework_file_table;
import com.example.demo.entity.key.homework_file_key;

public interface HomeworkFileRespository extends JpaRepository<homework_file_table,homework_file_key>{
	
	public List<homework_file_table> findAllByCourseidAndFbtime(String course_id,String fb_time);
	
	public void deleteAllByCourseidAndFbtime(String course_id,String fb_time);
	
	public List<homework_file_table> findAllByCourseidAndFbtimeOrderByFbtimeDesc(String course_id,String fb_time);
	
	public homework_file_table findByCourseidAndFilepath(String course_id,String file_path);
	
	public int countByCourseidAndFbtime(String course_id,String fb_time);

}
