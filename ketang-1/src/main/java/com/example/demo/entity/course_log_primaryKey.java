package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
@Embeddable
@Data
public class course_log_primaryKey implements Serializable {
    @Id
    @Column(name="course_id",insertable = false,updatable = false)
    private String courseid;
    @Id
    @Column(name="student_phone",insertable = false,updatable = false)
    private String studentphone;
}
