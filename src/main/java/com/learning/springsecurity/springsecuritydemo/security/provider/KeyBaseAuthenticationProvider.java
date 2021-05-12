package com.learning.springsecurity.springsecuritydemo.security.provider;

import com.learning.springsecurity.springsecuritydemo.security.authentication.KeyBaseAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class KeyBaseAuthenticationProvider implements AuthenticationProvider {

    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication.getName().equals(key)) {
            return new KeyBaseAuthentication(key, null , null);
        } else {
            throw new BadCredentialsException("Invalid Secret");
        }
    }

    @Override
    public boolean supports(Class<?> authType) {
        return KeyBaseAuthentication.class.equals(authType);
    }
}
