package com.example.demo.repository;

import com.example.demo.entity.course_log_primaryKey;
import com.example.demo.entity.course_log_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLogRepositpry extends JpaRepository<course_log_table, course_log_primaryKey> {

}
