package com.learning.springsecurity.springsecuritydemo.security.providers;

import com.learning.springsecurity.springsecuritydemo.security.authentication.UsernamePasswordAuthentication;
import com.learning.springsecurity.springsecuritydemo.service.JpaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailService jpaUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var username = authentication.getName();
        var password  = (String) authentication.getCredentials();

        var userDeatails = jpaUserDetailService.loadUserByUsername(username);

        if(passwordEncoder.matches(password , userDeatails.getPassword())) {
            return  new UsernamePasswordAuthentication(username,password ,userDeatails.getAuthorities());
        }

        throw new BadCredentialsException("Username or password is incorrect ");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
