package com.dubbo.service.impl;

import com.dubbo.bean.User;

public class UserServiceImpl implements  UserService {
    private int a;
    @Override
    public User getUser(int id) {
        User user = new User();
        user.setAge(id);
        user.setName(String.valueOf(a));
        return user;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}