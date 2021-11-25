package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.model.Role;
import com.backend.springboot.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	 private RoleRepository roleRepository;

	  
  public Role findById(Integer id) {
    Role auth = this.roleRepository.getOne(id);
    return auth;
  }

  public List<Role> findByName(String name) {
	List<Role> roles = this.roleRepository.findByName(name);
    return roles;
  }
}
