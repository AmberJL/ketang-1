package com.example.demo.repository;

import com.example.demo.entity.course_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<course_table,String> {

}
