package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user_table")
@Data
public class user_table {
    @Id
    private String user_phone;
    @Column(length = 1,nullable = false)
    private String identity;
    @Column(nullable = false)
    private String user_pwd;
}
