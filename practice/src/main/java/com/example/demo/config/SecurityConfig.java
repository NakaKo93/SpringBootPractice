package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.controller.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
            	.requestMatchers("/css/**").permitAll()
                .requestMatchers("/admin/signup", "/admin/signin").permitAll()
                .anyRequest().authenticated() 
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/admin/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(customLoginSuccessHandler)
                .failureUrl("/admin/signin?error=true")
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
        );

        return http.build();
    }
	
}
