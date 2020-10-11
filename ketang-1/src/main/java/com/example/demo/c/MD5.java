package com.example.demo.c;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MD5 {
	 public static String md5(String string) {

	        MessageDigest md5 = null;
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	            byte[] bytes = md5.digest(string.getBytes());
	            StringBuilder result = new StringBuilder();
	            for (byte b : bytes) {
	                String temp = Integer.toHexString(b & 0xff);
	                if (temp.length() == 1) {
	                    temp = "0" + temp;
	                }
	                result.append(temp);
	            }
	            return result.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	 public static void main(String[] args)
	 {
		 System.out.println(new Date().getTime());
		 Scanner in = new Scanner(System.in);
		 while(true)
			 
		 {
			 
			 String mobile = in.next();
			 System.out.println(md5_salt(md5(md5(mobile))));
		 }
		 
	 }
	 public static String md5_salt(String pwd)
	 {
		 	pwd = MD5.md5(pwd);
			String salt = pwd.substring(5);
			System.out.println("slat:"+salt);
			pwd += salt;
			System.out.println("pwd+slat:"+pwd);
			pwd = MD5.md5(pwd);
			System.out.println("finall:"+pwd);
			return pwd;
	 }
}
