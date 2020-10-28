package com.example.demo.Service;

import com.example.demo.data.studentData;

public interface StudentService {
    int insertStudent(studentData stu);
    studentData showInfo(studentData stu);
    int updateSInfo(studentData stu);
}
