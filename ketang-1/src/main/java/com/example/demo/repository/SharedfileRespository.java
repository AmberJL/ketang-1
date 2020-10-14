package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.sharedfile_table;
import com.example.demo.entity.key.sharedfile_key;

public interface SharedfileRespository extends JpaRepository<sharedfile_table,sharedfile_key>{

}
