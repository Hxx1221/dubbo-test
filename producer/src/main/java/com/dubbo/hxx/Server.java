package com.dubbo.hxx;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dubbo.service.impl.UserService;
import com.dubbo.service.impl.UserServiceImpl;

import java.io.IOException;

public class Server {


    public static void main(String[] args) throws IOException {
        ApplicationConfig config = new ApplicationConfig();
        config.setName("simple-app");
        ProtocolConfig dubbo = new ProtocolConfig("dubbo", -1);
        dubbo.setThreads(200);
        ServiceConfig<UserService> userServiceServiceConfig = new ServiceConfig<>();
        userServiceServiceConfig.setApplication(config);
        userServiceServiceConfig.setProtocol(dubbo);
        userServiceServiceConfig.setRegistry(new RegistryConfig("zookeeper://106.12.12.39:2181"));
        userServiceServiceConfig.setInterface(UserService.class);
        UserServiceImpl userService = new UserServiceImpl();
        userServiceServiceConfig.setRef(userService);
        userServiceServiceConfig.export();
        System.out.println("服务已开启!端口:" + userServiceServiceConfig.getExportedUrls().get(0).getPort());
        int s = userServiceServiceConfig.getExportedUrls().get(0).getPort();
        userService.setA(s);
        System.in.read();
    }
}