package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="teacher_table")
@Data
public class teacher_table {
    @Id
    private int teacher_id;

    @Column(nullable = false)
    private String teacher_name;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String department;


    @ManyToOne(cascade = CascadeType.ALL)//多对一的外键
    @JoinColumn(name = "school_id",referencedColumnName = "school_id")
    private school_table schoolTable;


    @OneToOne(cascade = CascadeType.ALL)//一对一的外键
    @JoinColumn(name = "teacher_phone",referencedColumnName = "user_phone")
    private user_table userTable;

}
