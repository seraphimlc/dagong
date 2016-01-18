package com.dagong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

/**
 * Created by liuchang on 16/1/15.
 */

@SpringBootApplication
public class MyApplication {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MyApplication.class);
        springApplication.setWebEnvironment(true);
        HashSet<Object> objects = new HashSet<>();
        objects.add("classpath:base/all.xml");
        SpringApplication.run(MyApplication.class,args);
    }
}
