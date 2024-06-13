package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.ContactDto;
import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());

        return "contact/create";
    }

    @PostMapping("/contact")
    public String contact(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult, HttpServletRequest request) {
        if (errorResult.hasErrors()) {
          return "contact/create";
        }

        HttpSession session = request.getSession();
        session.setAttribute("contactForm", contactForm);

        return "redirect:/contact/confirm";
    }

    @GetMapping("/contact/confirm")
    public String confirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);
        
        return "contact/confirmation";
    }

    @PostMapping("/contact/register")
    public String register(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");

        contactService.saveContact(contactForm);

        return "contact/completion";
    }

    @GetMapping("/admin/contacts")
    public String complete(Model model) {
    	List<ContactDto> dtoList = contactService.findAllContact();
        model.addAttribute("contactList", dtoList);

        return "contact/list";
      }
    
    @GetMapping("/admin/contacts/:{id}")
    public String detail(Model model, @PathVariable("id") int id) {
    	ContactDto dto = contactService.findContact(id);
    	model.addAttribute("contactDetail", dto);
    	
    	return "contact/detail";
    }
    
    @GetMapping("/admin/contacts/:{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
    	ContactDto dto = contactService.findContact(id);
    	ContactForm form = contactService.copyDtoToForm(dto);
    	
    	model.addAttribute("contactForm",form);
    	
    	return "contact/edit";
    }
    
    @PostMapping("/admin/contacts/:{id}/edit")
    public String editProcess(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult, @PathVariable("id") int id) {
		if (errorResult.hasErrors()) {
            return "contact/create";
          }
        
    	contactService.updateContact(contactForm);
    	
    	return "redirect:/admin/contacts";
    }
    
    @GetMapping("/admin/contacts/:{id}/delete")
    public String delete(Model model, @PathVariable("id") int id) {
    	contactService.deleteContact(id);
    	
    	return "redirect:/admin/contacts";
    }
}