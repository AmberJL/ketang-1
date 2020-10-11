package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="school_table")
@Data
public class school_table {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int school_id;
    @Column(nullable = false)
    private String school_name;
}
