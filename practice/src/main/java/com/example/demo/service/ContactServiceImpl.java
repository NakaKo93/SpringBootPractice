package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContactDto;
import com.example.demo.entity.ContactEntity;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    
    @Override
    public void saveContact(ContactForm form) {
    	ContactEntity entity = new ContactEntity();
    	
    	copyFormToEntity(form, entity);
    	contactRepository.save(entity);
    }
    
    @Override
    public List<ContactDto> findAllContact() {
    	List<ContactDto> dtoList = new ArrayList<>();
    	
    	List<ContactEntity> entityList = contactRepository.findAll();
    	entityList.forEach(entity -> {
    		ContactDto dto = new ContactDto();
    		
    		copyEntityToDto(entity, dto);
    		dtoList.add(dto);
    	});
    		
        return dtoList;
    }
    
    @Override
    public ContactDto findContact(int id) {
    	ContactDto dto = new ContactDto();
    	
    	ContactEntity entity = contactRepository.findById(id).get();
    	copyEntityToDto(entity, dto);
    	
    	return dto;
    }
    
    @Override
    public void updateContact(ContactForm form) {
    	ContactEntity entity = new ContactEntity();
    	
    	copyFormToEntity(form, entity);
    	contactRepository.save(entity);
    }
    
    @Override
    public void deleteContact(int id) {
    	contactRepository.deleteById(id);
    }
    
    public ContactForm copyDtoToForm(ContactDto dto) {
    	ContactForm form = new ContactForm();
    	
    	form.setId(dto.getId());
    	form.setLastName(dto.getLastName());
    	form.setFirstName(dto.getFirstName());
    	form.setEmail(dto.getEmail());
    	form.setPhone(dto.getPhone());
    	form.setZipCode(dto.getZipCode());
    	form.setAddress(dto.getAddress());
    	form.setBuildingName(dto.getBuildingName());
    	form.setContactType(dto.getContactType());
    	form.setBody(dto.getBody());
    	
    	return form;
    }
    
    private void copyEntityToDto(ContactEntity entity, ContactDto dto) {
    	dto.setId(entity.getId());
    	dto.setLastName(entity.getLastName());
    	dto.setFirstName(entity.getFirstName());
    	dto.setEmail(entity.getEmail());
    	dto.setPhone(entity.getPhone());
    	dto.setZipCode(entity.getZipCode());
    	dto.setAddress(entity.getAddress());
    	dto.setBuildingName(entity.getBuildingName());
    	dto.setContactType(entity.getContactType());
    	dto.setBody(entity.getBody());
    }
    
    private void copyFormToEntity(ContactForm form, ContactEntity entity) {
    	entity.setId(form.getId());
    	entity.setLastName(form.getLastName());
    	entity.setFirstName(form.getFirstName());
    	entity.setEmail(form.getEmail());
    	entity.setPhone(form.getPhone());
    	entity.setZipCode(form.getZipCode());
    	entity.setAddress(form.getAddress());
    	entity.setBuildingName(form.getBuildingName());
    	entity.setContactType(form.getContactType());
    	entity.setBody(form.getBody());
    }
}