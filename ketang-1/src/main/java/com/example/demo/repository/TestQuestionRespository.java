package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.test_question_table;
import com.example.demo.entity.key.test_question_key;

public interface TestQuestionRespository extends JpaRepository<test_question_table,test_question_key>{

}
