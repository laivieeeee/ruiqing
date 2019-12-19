package com.ruiqing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ruiqing.dao")
public class RqItemService {
    public static void main(String[] args) {
        SpringApplication.run(RqItemService.class, args);
    }
}
