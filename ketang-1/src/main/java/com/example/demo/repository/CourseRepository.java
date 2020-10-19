package com.example.demo.repository;

import com.example.demo.data.courseData;
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
    //通过教师手机号查询申报的课程信息并以时间为准进行降序查询
    public List<course_table> findAllByTeacherphoneOrderByTimeDesc(String phone);
    //通过课程号查询申报的课程信息并以时间为准进行降序查询
    //public List<courseData> findByCourseidOrderByTimeDesc(String course_id);

    public course_table findByCourseid(String id);
    //更新课程名称与介绍
    @Transactional
    @Modifying
    @Query(value="update course_table c set c.course_name =?1,c.course_introduce=?2 where c.course_id=?3",nativeQuery = true)
    public void updateCourse( String name,String introduce,String id);


}
