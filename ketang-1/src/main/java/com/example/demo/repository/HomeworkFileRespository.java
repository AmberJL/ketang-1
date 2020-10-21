package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.homework_file_table;
import com.example.demo.entity.key.homework_file_key;

public interface HomeworkFileRespository extends JpaRepository<homework_file_table,homework_file_key>{

}
