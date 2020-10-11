package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="student_table")
@Data
public class student_table {
    @Id
    private int stu_id;
    @Column(nullable = false)
    private String stu_name;
    @Column(length = 1,nullable = false)
    private String sex;
    @Column(length = 4,nullable = false)
    private String year;
    @ManyToOne
    @JoinColumn(name = "school_id",referencedColumnName = "school_id",nullable = false)
    private school_table schoolTable;
    @OneToOne
    @JoinColumn(name = "stu_phone",referencedColumnName = "user_phone",nullable = false)
    private user_table userTable;

}
