package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.test_question_log_table;
import com.example.demo.entity.key.test_question_log_key;

public interface TestQuestionLogRespository extends JpaRepository<test_question_log_table,test_question_log_key>{

}
