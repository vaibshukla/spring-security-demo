package com.learning.springsecurity.springsecuritydemo.security.authentication;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class KeyBaseAuthentication extends UsernamePasswordAuthenticationToken {

    public KeyBaseAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public KeyBaseAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
