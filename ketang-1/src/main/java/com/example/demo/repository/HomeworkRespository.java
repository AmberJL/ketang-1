package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.homework_table;
import com.example.demo.entity.key.course_time_key;

public interface HomeworkRespository extends JpaRepository<homework_table,course_time_key>{

}
