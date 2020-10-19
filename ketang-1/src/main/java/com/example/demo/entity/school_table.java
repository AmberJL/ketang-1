package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="school_table")
@Data
public class school_table {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name="school_id")
    private int schoolid;
    @Column(nullable = false)
    private String schoolname;
}
