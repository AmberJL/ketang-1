package com.example.demo.repository;

import com.example.demo.entity.school_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRespository extends JpaRepository<school_table,Integer> {
}
