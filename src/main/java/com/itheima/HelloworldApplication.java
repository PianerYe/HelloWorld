package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.Serializable;

@EnableCaching
@SpringBootApplication
public class HelloworldApplication implements Serializable{

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

}
