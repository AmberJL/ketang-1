package com.example.demo.Service;

import com.example.demo.data.userData;

public interface UserService {
    void Testlogin(String phone, String pwd);
    int signUp(userData user);
    int login(userData user);
    int forget_pwd(userData user);
    int infoCheck(userData user);
}
