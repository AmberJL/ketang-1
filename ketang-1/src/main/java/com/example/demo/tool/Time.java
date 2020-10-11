package com.example.demo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

//系统时间转字符串
public class Time {
	
	//日期格式
	private static SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//取当前时间
	public static String getTime() {
		return s.format(new Date());
	}
	
	//取(毫秒)之后的时间
	public static String getTimeNext(long time) {
		Date temp=new Date();
		return s.format(new Date(temp.getTime() + time));
	}
	
	//取(毫秒)之前后的时间
	public static String getTimeBefor(long time) {
		Date temp=new Date();
		return s.format(new Date(temp.getTime() - time));
	}
}