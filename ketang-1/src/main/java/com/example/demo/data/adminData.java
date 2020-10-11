package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class adminData {
    private int admin_id;
    private String admin_name;
    private String admin_phone;
    private String admin_Pwd;
}
