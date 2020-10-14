package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="course_table")
@Data
public class course_table {
    @Id
    private String course_id;
    @Column(nullable = false)
    private String course_name;
    @Column(nullable = false)
    private String teacher_phone;
    @Column
    private String course_introduce;

}
