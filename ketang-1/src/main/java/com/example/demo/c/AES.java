package com.example.demo.c;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;

//import org.bouncycastle.util.encoders.Base64;


public class AES {
	


	public static void main(String[] args) {
		HashMap<String,String> code = AES.encode("和三假按揭啊妇女节阿飞飞你家的烦恼都看了你咖啡店你看反馈13716521296");
		System.out.println("key:"+code.get("key")+" value:"+code.get("value"));
		System.out.println(decode(code));
	}
	//加密
	public static HashMap<String,String>  encode (String string){
		HashMap<String,String> res = new HashMap();
      try {
          //生成key
          KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
          //设置密钥长度
          keyGenerator.init(128);
          //生成密钥对象
          SecretKey secretKey = keyGenerator.generateKey();
          //获取密钥
          byte[] keyBytes = secretKey.getEncoded();
          //key转换
          Key key = new SecretKeySpec(keyBytes,"AES");
          
          //加密
          Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
          res.put("key", false_key(keyBytes));
          
          //初始化，设置为加密
          cipher.init(Cipher.ENCRYPT_MODE, key);
          byte[] result = cipher.doFinal(string.getBytes());
          res.put("value", Base64Utils.encodeToString(result));
          return res;
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      
  }
	public static String decode(HashMap<String,String> res)
	{
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, change_true_key((String)res.get("key")));
			return new String(cipher.doFinal(Base64Utils.decodeFromString((String)res.get("value"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	//生成 假key
	public static String false_key(byte[] b)
	{
		
		String temp =Base64Utils.encodeToString(b);
		System.out.println("keyByte:"+temp);
		char[] array = temp.toCharArray();
		for(int i = 0, j = array.length-3; i < j ; i++ , j--)
		{
			char t = array[i];
			array[i] = array[j];
			array[j] = t;
		}
		temp = new String(array);
		System.out.println("mmi:"+temp);
		return temp;
	}
	//假key 变 真key
	public static Key change_true_key(String f)
	{
		char[] array = f.toCharArray();
		for(int i = 0, j = array.length-3; i < j ; i++ , j--)
		{
			char t = array[i];
			array[i] = array[j];
			array[j] = t;
		}
		f = new String(array);
		System.out.println("after_change:"+f);
		return new SecretKeySpec(Base64Utils.decodeFromString(f),"AES");
		
	}
}