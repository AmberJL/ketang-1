package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userData {
    private String user_phone;
    private String identity;
    private String user_pwd;
    private String key;
}
