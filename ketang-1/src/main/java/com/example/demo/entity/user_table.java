package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user_table")
@Data
public class user_table {
    @Id
    @Column(name = "user_phone")
    private String userphone;
    @Column(length = 1,nullable = false,name = "identity")
    private String identity;
    @Column(nullable = false,name = "user_pwd")
    private String userpwd;
}
