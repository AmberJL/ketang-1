package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class messageData {
	private long message_id;
	private String to_user_id;
	private String from_user_id;
	private String content;
	private int state;
	private long time;
}
