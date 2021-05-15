package com.learning.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager udm = new InMemoryUserDetailsManager();

       var user1 = User.withUsername("vaibhav")
                .password("12345")
                .authorities("read")
                .build();

       udm.createUser(user1);
       return udm;
    }

    @Bean
    public PasswordEncoder  passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}
