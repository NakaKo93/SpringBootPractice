package com.example.demo.dto;

import lombok.Data;

@Data
public class ContactDto {
	private int id;
	
    private String lastName;

    private String firstName;

    private String email;

    private String phone;

    private String zipCode;

    private String address;

    private String buildingName;

    private String contactType;

    private String body;
}
