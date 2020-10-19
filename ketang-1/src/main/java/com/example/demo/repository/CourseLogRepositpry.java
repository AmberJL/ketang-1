package com.example.demo.repository;

import com.example.demo.data.courseLogData;
import com.example.demo.entity.course_log_primaryKey;
import com.example.demo.entity.course_log_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseLogRepositpry extends JpaRepository<course_log_table, course_log_primaryKey> {
    //学生删除课程用
    @Transactional
    @Modifying
    @Query(value="delete from course_log_table c where c.course_id =:id1",nativeQuery = true)
    public void deleteCourseInLog(@Param("id1") String course_id);
    //通过手机号与课程号删除选课
    @Transactional
    @Modifying
    @Query(value="delete from course_log_table c where c.course_id =?1 and c.student_phone =?2",nativeQuery = true)
    public void deleteByCourseidAndStudentphone(String id,String phone);
    @Query(value="select * from course_log_table c where c.course_id=:id1",nativeQuery = true)
    public List<course_log_table> selectCourseLogById(@Param("id1") String course_id);
    public int countByCourseid(String id);
    public List<course_log_table> findByStudentphone(String phone);
    public List<course_log_table> findAllByCourseid(String course_id);
}
