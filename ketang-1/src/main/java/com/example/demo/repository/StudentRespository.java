package com.example.demo.repository;

import com.example.demo.entity.student_table;
import com.example.demo.entity.teacher_table;
import com.example.demo.entity.user_table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRespository extends JpaRepository<student_table,String> {
	student_table findByPhone(String phone);
	
	//更改个人信息
	@Transactional
	@Modifying
	@Query(value="update student_table t set t.pic_id =:id1 , t.stu_name =:id2 , t.sex =:id3 , t.school_id =:id4 , t.stu_id =:id5 where t.phone =:id6",nativeQuery=true)
	public void updateInfo(@Param("id1") int picid,@Param("id2") String name,@Param("id3") String sex,@Param("id4") int school,@Param("id5") String stuid,@Param("id6") String phone);
	

}
