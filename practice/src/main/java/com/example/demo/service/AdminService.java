package com.example.demo.service;

import com.example.demo.dto.AdminDto;
import com.example.demo.form.AdminForm;

public interface AdminService {
	public boolean saveAdmin(AdminForm form);
	
	public AdminDto findAdmin(String email);
}
