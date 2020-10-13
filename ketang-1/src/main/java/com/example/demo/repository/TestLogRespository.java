package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.test_log_table;
import com.example.demo.entity.key.course_time_phone_key;

public interface TestLogRespository extends JpaRepository<test_log_table,course_time_phone_key>{

}
