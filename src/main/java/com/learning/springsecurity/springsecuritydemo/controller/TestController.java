package com.learning.springsecurity.springsecuritydemo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestController {

    private final Logger  logger = LoggerFactory.getLogger(TestController.class);


    @GetMapping(path = {"/" , "/test"})
    public String test(){

        Runnable r = () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            logger.info(" Authentication : {} " , authentication);
        };

      /*  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info(" Authentication : {} " , authentication);*/
        return "Working !!" ;
    }


}
