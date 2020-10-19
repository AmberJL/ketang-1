package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name="course_table")
@Data
public class course_table {
    @Id
    @Column(name = "course_id")
    private String courseid;
    @Column(nullable = false,name = "course_name")
    private String coursename;
    @Column(nullable = false,name = "teacher_phone")
    private String teacherphone;
    @Column(nullable = false,name="time")
    private String time;
    @Column(name = "course_introduce")
    private String courseintroduce;

}
