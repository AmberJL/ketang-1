package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.notice_table;
import com.example.demo.entity.key.course_time_key;

public interface NoticeRespository extends JpaRepository<notice_table,course_time_key>{
	
	public List<notice_table> findAllByCourseidOrderByFbtimeDesc(String course_id);

}
