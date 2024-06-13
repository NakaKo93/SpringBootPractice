package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdminEntity;
import com.example.demo.model.AdminDetails;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminLoginServiceImpl implements UserDetailsService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Optional<AdminEntity> adminOps = adminRepository.findByEmail(email);
	    
	    if (!adminOps.isPresent()) {
	    	throw new UsernameNotFoundException("アカウントが見つかりません");
	    }
	    
	    AdminDetails details = new AdminDetails(adminOps.get());
	    
    	return details;
	 }
	
    public void updateDateTime(String email) {
		AdminEntity entity = adminRepository.findByEmail(email).get();
	    
    	entity.setCurrent_sign_in_at(getDateTime());
    	adminRepository.save(entity);
	}
	
	private String getDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        return formattedDateTime;
	}
}
