package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="teacher_table")
@Data
public class teacher_table {
    @Id
    @Column(name = "phone")
    private String phone;

    @Column(nullable = false,name = "teacher_name")
    private String teachername;

    @Column(nullable = false,name = "sex")
    private String sex;

    @Column(name = "department")
    private String department;
    @Column(nullable = false,name = "school_id")
    private int schoolid;
    @Column(name = "teacher_id")
    private String teacherid;


}
