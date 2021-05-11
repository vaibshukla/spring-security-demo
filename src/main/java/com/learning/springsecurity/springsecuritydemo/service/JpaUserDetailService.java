package com.learning.springsecurity.springsecuritydemo.service;

import com.learning.springsecurity.springsecuritydemo.entity.User;
import com.learning.springsecurity.springsecuritydemo.model.SecuredUser;
import com.learning.springsecurity.springsecuritydemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;



public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return new SecuredUser(user.get());
        }

        throw new BadCredentialsException("Username is not available");


    }
}
