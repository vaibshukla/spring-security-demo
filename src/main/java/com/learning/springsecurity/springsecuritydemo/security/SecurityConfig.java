package com.learning.springsecurity.springsecuritydemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {
       var inMemUDM = new InMemoryUserDetailsManager();

       var user =  User.withUsername("vaibhav")
                .password("test")
                 .authorities(List.of(()  -> "read"))
                .build();

       inMemUDM.createUser(user);

       return inMemUDM;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
