package com.example.demo.data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  studentData {
    private int stu_id;
    private String stu_name;
    private String sex;
    private String year;
    private int school_id;
    private String stu_phone;
}
