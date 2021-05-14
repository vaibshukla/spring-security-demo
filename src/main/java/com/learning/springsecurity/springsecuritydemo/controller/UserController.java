package com.learning.springsecurity.springsecuritydemo.controller;

import com.learning.springsecurity.springsecuritydemo.vo.UserVo;
import com.learning.springsecurity.springsecuritydemo.model.SecuredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")

public class UserController {

    @Autowired
    @Qualifier(value = "userDetailsManager")
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Async
    @PostMapping
    public String addUser(@RequestBody UserVo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsManager.createUser(user);
        return "User Created";
    }
}
