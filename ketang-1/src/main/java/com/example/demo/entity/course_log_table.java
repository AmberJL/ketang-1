package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="course_log_table")
@Data
@IdClass(course_log_primaryKey.class)
public class course_log_table {
    @EmbeddedId
    private course_log_primaryKey primaryKey;
    @Id
    @Column(name = "course_id")
    private String course_id;
    @Id
    @Column
    private String student_phone;
}
