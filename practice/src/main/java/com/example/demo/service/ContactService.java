package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ContactDto;
import com.example.demo.form.ContactForm;

public interface ContactService {
	void saveContact(ContactForm contactForm);
    
    List<ContactDto> findAllContact();
    
    ContactDto findContact(int id);
    
    void updateContact(ContactForm form);
    
    void deleteContact(int id);
    
    ContactForm copyDtoToForm(ContactDto dto);
}