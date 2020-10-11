package com.example.demo.Service;

import com.example.demo.entity.school_table;
import com.example.demo.entity.user_table;

public interface TeacherService {
    void insertTeacher(int id, String name, String sex, String department, int school_id, String phone);
}
