package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="course_table")
@Data
public class course_table {
    @Id
    private int course_id;
    @Column(nullable = false)
    private String course_name;
    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id",nullable = false)
    private teacher_table teacher_id;

}
