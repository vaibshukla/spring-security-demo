package com.learning.springsecurity.springsecuritydemo.security.filters;

import com.learning.springsecurity.springsecuritydemo.manager.TokenManager;
import com.learning.springsecurity.springsecuritydemo.security.authentication.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    public TokenManager tokenManager;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token =  request.getHeader("Authorization");
        Authentication a =  new TokenAuthentication(token , null);
        var  authenticate     =   authenticationManager.authenticate(a);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        filterChain.doFilter(request , response);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/login") ||
                request.getServletPath().equals("/user");
    }
}
