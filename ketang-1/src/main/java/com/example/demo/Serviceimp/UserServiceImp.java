package com.example.demo.Serviceimp;

import com.example.demo.Service.UserService;
import com.example.demo.c.AES;
import com.example.demo.c.MD5;
import com.example.demo.data.userData;
import com.example.demo.entity.admin_table;
import com.example.demo.entity.student_table;
import com.example.demo.entity.user_table;
import com.example.demo.repository.AdminRespository;
import com.example.demo.repository.StudentRespository;
import com.example.demo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRespository userRespository;
    
    @Autowired
    private StudentRespository student;

    public void Testlogin(String phone, String pwd) {

        if(userRespository.findUser(phone,pwd)!=null){
            System.out.println("登录成功！");
        }
        else {
            System.out.println("登录失败");
        }
        //System.out.println(userRespository.findByPhoneAndPwd(phone,pwd));
    }

	@Override
	public int signUp(userData user) {
		// TODO Auto-generated method stub
		String pwd = user.getUser_pwd();
		System.out.println(user.getUser_phone()+" get "+ pwd + "key:"+user.getKey()+" phone:"+user.getUser_phone());
		pwd = MD5.md5_salt(pwd);
		System.out.println(user.getUser_phone()+" final: "+ pwd);
		HashMap<String,String> t = new HashMap();
		t.put("key", user.getKey());
		t.put("value", user.getUser_phone());
		String phone = AES.decode(t);
		System.out.println("phone:"+phone+" identity:"+user.getIdentity()+"final: "+ pwd);
		user_table u = new user_table();
		u.setUser_phone(phone);
		u.setIdentity(user.getIdentity());
		u.setUser_pwd(pwd);
		try {
			try{
				user_table chong = this.userRespository.findById(phone).get();
				return -1;
			}catch (Exception e)
			{
				this.userRespository.save(u);
				return 1;
			}
		}catch (Exception e)
		{
			return 0;
		}

	}

	@Override
	public int login(userData user) {
		// TODO Auto-generated method stub
		String pwd = user.getUser_pwd();
		System.out.println(user.getUser_phone()+" get "+ pwd + "key:"+user.getKey()+" phone:"+user.getUser_phone());
		pwd = MD5.md5_salt(pwd);
		System.out.println(user.getUser_phone()+" final: "+ pwd);
		HashMap<String,String> t = new HashMap();
		t.put("key", user.getKey());
		t.put("value", user.getUser_phone());
		String phone = AES.decode(t);
		System.out.println("phone:"+phone+"final: "+ pwd);
		List<user_table> res = this.userRespository.login(phone, pwd);
		
		if(res.size() == 1)
		{
			user_table u = res.get(0);
			List<student_table> s = student.checkInfo(phone);
			if(s.size() == 1)
			{
				//学生400 老师500
				return u.getIdentity().equals("S") ? 400 : 500;	
			}else {
				//未完善信息 402
				return 402;
			}
			
		}else {
			return 401;
		}
	}

	@Override
	public int forget_pwd(userData user) {
		// TODO Auto-generated method stub
		try {
			String pwd = MD5.md5_salt(user.getUser_pwd());
			HashMap<String,String> t = new HashMap();
			t.put("key", user.getKey());
			t.put("value", user.getUser_phone());
			String phone = AES.decode(t);
			System.out.println("修改密码phone:"+phone+"final: "+ pwd);
			this.userRespository.forget_pwd(pwd, phone);
			return 201;
		}catch(Exception e) {
			
			e.printStackTrace();
			return 400;
		}
		
	}




}
