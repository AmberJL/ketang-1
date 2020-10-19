package com.example.demo.repository;

import com.example.demo.entity.student_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<student_table,String> {
}
