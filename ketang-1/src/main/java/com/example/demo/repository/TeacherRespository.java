package com.example.demo.repository;

import com.example.demo.data.teacherData;
import com.example.demo.entity.teacher_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRespository extends JpaRepository<teacher_table,String> {
    teacher_table findByPhone(String phone);
}
