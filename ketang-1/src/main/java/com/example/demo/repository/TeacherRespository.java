package com.example.demo.repository;

import com.example.demo.data.teacherData;
import com.example.demo.entity.teacher_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherRespository extends JpaRepository<teacher_table,String> {
    teacher_table findByPhone(String phone);
    
    //更改教师信息
  	@Transactional
  	@Modifying
  	@Query(value="update teacher_table t set t.pic_id =:id1 , t.teacher_name =:id2 , t.sex =:id3 , t.school_id =:id4 , t.teacher_id =:id5 , t.department =:id6 where t.phone =:id7",nativeQuery=true)
  	public void updateInfo(@Param("id1") int picid,@Param("id2") String name,@Param("id3") String sex,@Param("id4") int school,@Param("id5") String stuid,@Param("id6") String deparment,@Param("id7") String phone);
}
