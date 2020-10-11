package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="message_table")
@Data
public class message_table {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int message_id;
	 @Column(nullable = false)
	 private String to_user_id;
	 @Column(nullable = false)
	 private String from_user_id;
	 @Column(nullable = false)
	 private String content;
	 @Column(nullable = false)
	 private long time;
	 @Column(nullable = false)
	 private int state;
	 
     
}

