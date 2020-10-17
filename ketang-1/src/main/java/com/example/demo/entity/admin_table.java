package com.example.demo.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_table")
@Data

public class admin_table {
    @Id
    @Column(name = "admin_id")
    private int adminid;
    @Column(nullable = false,name = "admin_name")
    private String adminname;
    @Column(nullable = false,name = "admin_phone")
    private String adminphone;
    @Column(nullable = false,name = "admin_pwd")
    private String adminpwd;

}
