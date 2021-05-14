package com.learning.springsecurity.springsecuritydemo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final Logger  logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(path = {"/" , "/test"})
    public String test(Authentication authentication){
        logger.info(" Authentication : {} " , authentication);
        return "Working !!" + authentication.getName();
    }


}
