package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.homework_log_table;
import com.example.demo.entity.key.homework_log_key;

public interface HomeworkLogRespository extends JpaRepository<homework_log_table,homework_log_key>{

}
