package com.anoop.examples.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String[] ignoreUrls = {"/actuator/**", "/v2/api-docs",
            "/swagger-resources/**", "/swagger-ui/**", "/webjars/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ignoreUrls).permitAll()
                .antMatchers("/v1/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
    }
}
