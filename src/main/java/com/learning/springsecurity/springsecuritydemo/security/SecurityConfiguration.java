package com.learning.springsecurity.springsecuritydemo.security;


import com.learning.springsecurity.springsecuritydemo.security.filter.PreDefinedKeyBaseAuthenticationFilter;
import com.learning.springsecurity.springsecuritydemo.security.provider.KeyBaseAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@AllArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final KeyBaseAuthenticationProvider keyBaseAuthenticationProvider;
    private final PreDefinedKeyBaseAuthenticationFilter preDefinedKeyBaseAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keyBaseAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Adding our created filter at BasicAuthenticationFilter
        http.addFilterAt(preDefinedKeyBaseAuthenticationFilter , BasicAuthenticationFilter.class);
    }
}

