package com.example.micrigramm.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityCongiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**")
                .fullyAuthenticated();

        http.authorizeRequests()
                .antMatchers("/subscribes/api/**")
                .fullyAuthenticated();

        http.authorizeRequests()
                .antMatchers("/publications/api/**")
                .fullyAuthenticated();


        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.httpBasic();

        http.formLogin().disable().logout().disable();

        http.csrf().disable();
    }
}