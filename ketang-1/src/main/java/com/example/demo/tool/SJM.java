package com.example.demo.tool;

//随机码
public class SJM {
	
	//取数字随机码(位数) 用于签到码
	public static String getNum(int size) {
		String temp="";
		for(int i=0;i<size;i++) {
			temp=temp+((int)(Math.floor(Math.random()*10)));
		}
		return temp;
	}
	
	//取数字加字母大写(位数) 用于课程码
	public static String getNumLet(int size) {
		String temp="";
		for(int i=0;i<size;i++) {
			int temp2=((int)(Math.floor(Math.random()*36)));
			if(temp2>9)temp+=(char)(temp2-10+'A');
			else temp+=temp2;
		}
		return temp;
	}
}
