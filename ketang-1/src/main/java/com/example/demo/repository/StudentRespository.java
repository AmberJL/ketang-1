package com.example.demo.repository;

import com.example.demo.entity.student_table;
import com.example.demo.entity.user_table;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRespository extends JpaRepository<student_table,Integer> {
	//检查信息完善
		@Query(value="select * from student_table t where t.user_phone =:id1", nativeQuery=true)
		public List<student_table> checkInfo(@Param("id1") String phone);
}
