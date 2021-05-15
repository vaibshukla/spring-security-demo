package com.learning.springsecurity.springsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class TestController {

    @GetMapping(path = {"/" , "/test"})
    public String main() {
        return "main.html";
    }

    @PostMapping("/change")
    public String changeResourceState() {
        log.info(" Change resource ");
        return "main.html";
    }

}
