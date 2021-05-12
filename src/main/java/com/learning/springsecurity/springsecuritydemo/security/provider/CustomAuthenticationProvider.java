package com.learning.springsecurity.springsecuritydemo.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var username =  authentication.getName();
        var pssword = String.valueOf(authentication.getCredentials());

        try {
            var usedetail = userDetailsService.loadUserByUsername(username);
            if (passwordEncoder.matches(pssword , usedetail.getPassword())) {
                var a = new UsernamePasswordAuthenticationToken(username , pssword , usedetail.getAuthorities());
                return a;
            } else {
                throw new BadCredentialsException("Username or Password is incorrect") ;
            }

        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Not able to get the username" , e) ;
        }



    }

    @Override
    public boolean supports(Class<?> authType) {

        // Supporting the UsernamePassword type of Authentication
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }
}
