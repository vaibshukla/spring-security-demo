package com.learning.springsecurity.springsecuritydemo.security.filters;

import com.learning.springsecurity.springsecuritydemo.entity.Otp;
import com.learning.springsecurity.springsecuritydemo.manager.TokenManager;
import com.learning.springsecurity.springsecuritydemo.repository.OtpRepository;
import com.learning.springsecurity.springsecuritydemo.security.authentication.OtpAuthentication;
import com.learning.springsecurity.springsecuritydemo.security.authentication.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;


public class TwoFactorAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        var username =  request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");

        if (otp == null ) {

            Authentication usernamePasswordAuthentication  = new UsernamePasswordAuthentication(username , password);
            usernamePasswordAuthentication = authenticationManager.authenticate(usernamePasswordAuthentication);
            Otp otpEntity =  new Otp();
            otpEntity.setUsername(username);
            String code  =  String.valueOf(new Random().nextInt(9999) + 1000);
            otpEntity.setOtp(code);
            otpRepository.save(otpEntity);
            response.setHeader("OTP" , code);

        }else {
            Authentication otpAuthentication =  new OtpAuthentication(username , otp);
            otpAuthentication = authenticationManager.authenticate(otpAuthentication);
            var token = UUID.randomUUID().toString();
            tokenManager.add(token);
            response.setHeader("token" , token);
        }

    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // Only Apply filter for login request
        return !request.getServletPath().equals("/login");
    }
}
