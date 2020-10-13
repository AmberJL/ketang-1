package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.test_table;
import com.example.demo.entity.key.course_time_key;

public interface TestRespository extends JpaRepository<test_table,course_time_key>{

}
