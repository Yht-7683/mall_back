package com.yht.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MallSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSysApplication.class, args);
    }

}
