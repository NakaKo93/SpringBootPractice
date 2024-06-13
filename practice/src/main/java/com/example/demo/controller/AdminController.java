package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminServiceImpl;

@Controller
public class AdminController {
	@Autowired
	private AdminServiceImpl adminServise;
	
    @GetMapping("/")
    public String redirectToSigin() {
    	return "redirect:/admin/contacts";
    }
	
	@GetMapping("/admin/signup")
	public String adminSiginup(Model model) {
        model.addAttribute("adminForm", new AdminForm());
		return "admin/signup";
	}
	
	@PostMapping("/admin/signup")
    public String adminSiginup(Model model, @Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult) {
        if (errorResult.hasErrors()) {
          return "admin/signup";
        }

        boolean result = adminServise.saveAdmin(adminForm);
        if (!result) {
        	String error = "登録に失敗しました";
        	model.addAttribute("error", error);
        	
        	return "admin/signup";
        }
        
    	return "redirect:/admin/signin";
    }
	
	@GetMapping("/admin/signin")
	public String adminSiginin() {
		return "admin/signin";
	}
	
	@GetMapping("/admin/logout")
    public String redirectToSiginin() {
        return "redirect:/admin/signin";
    }
}
