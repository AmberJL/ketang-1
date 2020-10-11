package com.example.demo.c;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
	private String name;
	private String password;
	private String key;
	private String phone;
	private String identity;
}
