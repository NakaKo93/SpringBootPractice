package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public boolean saveAdmin(AdminForm form) {
    	AdminEntity entity = new AdminEntity();
    	
    	Optional<AdminEntity> adminOps = adminRepository.findByEmail(form.getEmail());
    	
    	if (adminOps.isPresent()) {
	    	return false;
	    }
    	
    	copyFormToEntity(form, entity);
    	entity.setCurrent_sign_in_at(getDateTime());
    	adminRepository.save(entity);
    	
    	return true;
	}

    @Override
	public AdminDto findAdmin(String email) {
		AdminDto dto = new AdminDto();
		AdminEntity entity = adminRepository.findByEmail(email).get();
    	
		copyEntityToDtoy(dto, entity);
    	
    	return dto;
	}
	
	private String getDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        return formattedDateTime;
	}
	
	private void copyFormToEntity(AdminForm form, AdminEntity entity) {
		entity.setLastName(form.getLastName());
		entity.setFirstName(form.getFirstName());
		entity.setEmail(form.getEmail());
		entity.setPassword(passwordEncoder.encode(form.getPassword()));
	}
	
	private void copyEntityToDtoy(AdminDto dto, AdminEntity entity) {
		dto.setLastName(entity.getLastName());
		dto.setFirstName(entity.getFirstName());
		dto.setEmail(entity.getEmail());
	}
}
