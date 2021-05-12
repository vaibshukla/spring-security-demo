package com.learning.springsecurity.springsecuritydemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = {"/" , "/test"})
    public String test(){
        return "Working !!";
    }


}
