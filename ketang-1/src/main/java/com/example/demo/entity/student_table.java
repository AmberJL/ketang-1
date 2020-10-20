package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="student_table")
@Data
public class student_table {
    @Id
    @Column(name = "phone")
    private String phone;
    @Column(nullable = false,name = "stu_name")
    private String stuname;
    @Column(length = 1,nullable = false,name = "sex")
    private String sex;
    @Column(length = 4,nullable = false,name = "year")
    private String year;
    @Column(nullable = false,name = "school_id")
    private int schoolid;
    @Column(name = "stu_id")
    private String stuid;
    @Column(name = "pic_id")
    private int pic_id;

}
