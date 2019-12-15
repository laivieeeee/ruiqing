package com.ruiqing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RqRegistry {
    public static void main(String[] args) {
        SpringApplication.run(RqRegistry.class, args);
    }
}
