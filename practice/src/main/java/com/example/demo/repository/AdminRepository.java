package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
	Optional<AdminEntity> findByEmail(String email);
}