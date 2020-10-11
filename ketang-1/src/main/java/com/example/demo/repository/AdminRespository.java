package com.example.demo.repository;

import com.example.demo.entity.admin_table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<admin_table,Integer> {

}
