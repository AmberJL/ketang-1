package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class teacherData {
    private String teacher_name;
    private String sex;
    private String department;
    private int school_id;
    private String teacher_phone;
    private String teacher_id;
}
