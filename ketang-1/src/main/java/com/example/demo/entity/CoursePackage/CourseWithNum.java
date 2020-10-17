package com.example.demo.entity.CoursePackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithNum {
    private String course_id;
    private String course_name;
    private String teacher_phone;
    private String course_introduce;
    private String time;
    private int num;
}
