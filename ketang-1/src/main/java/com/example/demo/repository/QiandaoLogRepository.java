package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.qiandao_log_table;
import com.example.demo.entity.key.course_time_phone_key;

public interface QiandaoLogRepository extends JpaRepository<qiandao_log_table,course_time_phone_key>{
	
	public void deleteAllByCourseidAndFbtime(String course_id,String fb_time);
	
	public int countByCourseidAndFbtimeAndValue(String course_id,String fb_time,String value);
	

}
