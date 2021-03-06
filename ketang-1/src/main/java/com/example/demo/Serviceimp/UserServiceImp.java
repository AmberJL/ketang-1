package com.example.demo.Serviceimp;

import com.example.demo.Service.UserService;
import com.example.demo.c.AES;
import com.example.demo.c.MD5;
import com.example.demo.data.userData;
import com.example.demo.entity.admin_table;
import com.example.demo.entity.student_table;
import com.example.demo.entity.teacher_table;
import com.example.demo.entity.user_table;
import com.example.demo.repository.AdminRespository;
import com.example.demo.repository.StudentRespository;
import com.example.demo.repository.TeacherRespository;
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
    @Autowired
    private TeacherRespository teacher;

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
		u.setUserphone(phone);
		u.setIdentity(user.getIdentity());
		u.setUserpwd(pwd);
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
			String identity = u.getIdentity();
			try {
				if(identity.equals("S")){
					System.out.println("检查学生信息完善？");
					student_table st = this.student.findByPhone(u.getUserphone());
					if(st == null)
						return 801;
					return 400;
				}else{
					System.out.println("检查老师信息完善？");
					teacher_table tea = this.teacher.findByPhone(u.getUserphone());
					if(tea == null)
						return 802;
					return 500;
				}
				
			}catch(Exception e) {
				System.out.println("800");
				return 800;
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

	@Override
	public String showIdentity(String phone) {
		// TODO Auto-generated method stub
		user_table t = this.userRespository.findByUserphone(phone);
		return t.getIdentity();
	}

//	@Override
//	public int infoCheck(userData user) {
//		// TODO Auto-generated method stub
////		String id = user.getIdentity();
////		try {
////			
////		}catch(Exception) {
////			
////		}
////		if(id.equals("S"))
////		{
////			student_table t =  this.student.findByPhone(user.getUser_phone());
////		}else if(id.equals("T")) {
////			
////		}
//		return 0;
//	}




}
