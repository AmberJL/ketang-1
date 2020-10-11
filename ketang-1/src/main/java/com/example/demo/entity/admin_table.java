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
    private int admin_id;
    @Column(nullable = false)
    private String admin_name;
    @Column(nullable = false)
    private String admin_phone;
    @Column(nullable = false)
    private String admin_pwd;

}
