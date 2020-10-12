package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="teacher_table")
@Data
public class teacher_table {
    @Id
    private String phone;

    @Column(nullable = false)
    private String teacher_name;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private int school_id;


}
