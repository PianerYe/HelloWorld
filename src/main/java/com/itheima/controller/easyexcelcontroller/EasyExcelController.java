package com.itheima.controller.easyexcelcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("easyexcel")
public class EasyExcelController {

    @GetMapping
    public String easyexcel(){
        System.out.println("Hello World...222");
        return "OK2223333";
    }
}
