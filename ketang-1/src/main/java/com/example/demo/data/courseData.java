package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class courseData {
    private String course_id;
    private String course_name;
    private String teacher_phone;
    private String course_introduce;
    private String time;
}
