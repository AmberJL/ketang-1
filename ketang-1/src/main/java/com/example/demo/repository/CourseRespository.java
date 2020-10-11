package com.example.demo.repository;

import com.example.demo.entity.course_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRespository extends JpaRepository<course_table,Integer> {
}
