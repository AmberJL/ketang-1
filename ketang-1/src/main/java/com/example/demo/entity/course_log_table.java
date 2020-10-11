package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="course_log_table")
@Data

public class course_log_table {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "course_id",nullable = false)
    private course_table courseTable;
    @ManyToOne
    @JoinColumn(name = "stu_id",referencedColumnName = "stu_id",nullable = false)
    private student_table studentTable;
}
