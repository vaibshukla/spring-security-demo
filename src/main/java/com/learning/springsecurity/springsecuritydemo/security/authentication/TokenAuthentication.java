package com.learning.springsecurity.springsecuritydemo.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken {

    public TokenAuthentication(Object principal, Object credentials) {
        super(principal, credentials );
    }

    public TokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
