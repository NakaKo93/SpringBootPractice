package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.service.AdminLoginServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    
    @Autowired
    private AdminLoginServiceImpl adminLoginServiceImpl;
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, HttpServletResponse response,Authentication authentication) 
					throws IOException, ServletException {
		String email = authentication.getName();
		
		adminLoginServiceImpl.updateDateTime(email);
		
		response.sendRedirect("/admin/contacts");
	}
}