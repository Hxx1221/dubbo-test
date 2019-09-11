package com.dubbo.hxx;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.dubbo.bean.User;
import com.dubbo.service.impl.UserService;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{
        ApplicationConfig config = new ApplicationConfig();
        config.setName("young-app");

        ReferenceConfig<UserService> userServiceReferenceConfig = new ReferenceConfig<>();
        userServiceReferenceConfig.setApplication(config);

        userServiceReferenceConfig.setInterface(UserService.class);
        userServiceReferenceConfig.setRegistry(new RegistryConfig("zookeeper://106.12.12.39:2181"));
        userServiceReferenceConfig.setTimeout(5000);
        userServiceReferenceConfig.setLoadbalance("myOddNumber");
        UserService userService = userServiceReferenceConfig.get();

        String cmd="1";
        while (!(cmd = read()).equals("exit")) {
            Integer integer = Integer.valueOf(cmd);
            User user = userService.getUser(integer);
            System.out.println(user);
        }


    }

    private static String read() throws IOException {
        byte[] b = new byte[1024];
        int size = System.in.read(b);
        return new String(b, 0, size).trim();
    }
}