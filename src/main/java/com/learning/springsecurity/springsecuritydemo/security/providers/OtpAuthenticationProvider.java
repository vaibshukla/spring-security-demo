package com.learning.springsecurity.springsecuritydemo.security.providers;

import com.learning.springsecurity.springsecuritydemo.entity.Otp;
import com.learning.springsecurity.springsecuritydemo.repository.OtpRepository;
import com.learning.springsecurity.springsecuritydemo.security.authentication.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username =  authentication.getName();
        String otp = (String) authentication.getCredentials();

        Optional<Otp> otpEntity = otpRepository.findByUsername(username);

        if(otpEntity.isPresent()) {

            if (otpEntity.get().getOtp().equals(otp)) {
                return new OtpAuthentication(username, otp , List.of(() -> "read"));
            }
        }else {
            throw new BadCredentialsException("Not a valid Otp");
        }

        throw new BadCredentialsException("Not a valid Otp") ;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
