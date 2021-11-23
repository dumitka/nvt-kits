package com.backend.springboot.repository;


import com.backend.springboot.model.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	List<Role> findByName(String name);
}
