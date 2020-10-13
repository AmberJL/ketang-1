package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.notice_table;
import com.example.demo.entity.key.course_time_key;

public interface NoticeRespository extends JpaRepository<notice_table,course_time_key>{

}
