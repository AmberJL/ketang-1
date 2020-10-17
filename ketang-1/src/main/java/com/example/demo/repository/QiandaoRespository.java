package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.qiandao_table;
import com.example.demo.entity.key.course_time_key;

public interface QiandaoRespository extends JpaRepository<qiandao_table,course_time_key>{
	
	public List<qiandao_table> findByCourseidOrderByFbtimeDesc(String courseid);
	
}
