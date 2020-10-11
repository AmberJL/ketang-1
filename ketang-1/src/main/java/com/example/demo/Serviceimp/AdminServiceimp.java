package com.example.demo.Serviceimp;

import com.example.demo.Service.AdminService;
import com.example.demo.entity.admin_table;
import com.example.demo.repository.AdminRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimp implements AdminService {
    @Autowired
    private AdminRespository adminRespository;
    @Override
    public void InsertAdmin(int id, String name, String pwd, String phone) {
        admin_table adminTable = new admin_table();
        adminTable.setAdmin_id(id);
        adminTable.setAdmin_name(name);
        adminTable.setAdmin_phone(phone);
        adminTable.setAdmin_pwd(pwd);
        System.out.println("添加成功");
        adminRespository.save(adminTable);
    }
}
