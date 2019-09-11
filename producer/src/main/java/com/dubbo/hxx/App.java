package com.dubbo.hxx;

import com.dubbo.bean.User;
import com.dubbo.service.impl.UserService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Iterator<UserService> iterator = ServiceLoader.load(UserService.class).iterator();
        UserService next = iterator.next();
        User user = next.getUser(10);
        System.out.println(user);
    }
}
