package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="student_table")
@Data
public class student_table {
    @Id
    private String phone;
    @Column(nullable = false)
    private String stu_name;
    @Column(length = 1,nullable = false)
    private String sex;
    @Column(length = 4,nullable = false)
    private String year;
    @Column(nullable = false)
    private int school_id;

}
