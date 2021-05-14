package com.learning.springsecurity.springsecuritydemo.security.providers;

import com.learning.springsecurity.springsecuritydemo.manager.TokenManager;
import com.learning.springsecurity.springsecuritydemo.security.authentication.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var token =  authentication.getName();

        if (tokenManager.contains(token) ) {
            return new TokenAuthentication(token , null , List.of());
        }

        throw new BadCredentialsException("Invalid Token ");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
