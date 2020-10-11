package com.example.demo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	private static SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String getTime() {
		return s.format(new Date());
	}
	public static String getTimeNext(long time) {
		Date temp=new Date();
		return s.format(new Date(temp.getTime() + time));
	}
	public static String getTimeBefor(long time) {
		Date temp=new Date();
		return s.format(new Date(temp.getTime() - time));
	}
}