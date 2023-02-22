package com.itheima.controller.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping("")
    public String hello(){
        System.out.println("Hello World...222");
        return "OK2223333";
    }

}
