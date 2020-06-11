package com.example.annotation.controller;

import com.example.annotation.common.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Log(className = "TestController")
    @RequestMapping("/test")
    public String send(String a){
        return "Hello";
    }



}
