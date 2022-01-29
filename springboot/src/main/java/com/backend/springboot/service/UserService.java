package com.backend.springboot.service;

import com.backend.springboot.dto.CreateUpdateUserDto;
import com.backend.springboot.model.Role;
import com.backend.springboot.model.User;
import com.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private RoleService roleService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public List<User> getAllEmployees() {
		return userRepository.findByFiredFalse();
	}

	public User registerUser(User user, String roleName) {
		String password = user.getPassword();
		user.setPassword(encoder().encode(password));

		User savedUser = userRepository.save(user);

		return savedUser;
	}

	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Integer id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public User updateUser(User user, CreateUpdateUserDto dto) {

		if (dto.getPassword() != null) {
			user.setPassword(encoder().encode(dto.getPassword()));
		}
		if (dto.getName() != null) {
			user.setName(dto.getName());
		}
		if (dto.getLastName() != null) {
			user.setLastName(dto.getLastName());
		}
		if (dto.getUsername() != null) {
			user.setUsername(dto.getUsername());
		}
		if (dto.getRoleName() != null) {
			user.setRoles(roleService.findByName(dto.getRoleName()));
		}

		user = userRepository.save(user);

		if (dto.getSalary() != 0) {
			salaryService.createNewSalary(user, dto.getSalary());
		}

		return user;
	}

	public User deleteEmployee(Integer id) {
		User user = userRepository.getById(id);
		if (user == null) {
			return null;
		}
		user.setFired(true);
		return userRepository.save(user);
	}
}
