package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
