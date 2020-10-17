package com.example.demo.repository;

import com.example.demo.entity.CoursePackage.CourseWithNum;
import com.example.demo.entity.course_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<course_table,String> {
    //教师删除课程用
    @Transactional
    @Modifying
    @Query(value="delete from course_table c where c.course_id =:id1",nativeQuery = true)
    public void deleteCourse(@Param("id1") String course_id);
    //根据课程号查找课程
    @Query(value="select * from course_table c where c.course_id=:id1",nativeQuery = true)
    public List<course_table> selectCourseById(@Param("id1") String course_id);
    //根据教师手机号查找课程
    @Query(value="select * from course_table c where c.teacher_phone=:id1",nativeQuery = true)
    public List<course_table> selectCourseByPhone(@Param("id1") String teacher_phone);
    public List<course_table> findAllByTeacherphoneOrderByTimeDesc(String phone);


}
