package com.backend.springboot.service;

import com.backend.springboot.model.User;
import com.backend.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		return userRepository.findByFired(false);
	}

	public User registerUser(User user) {
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

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}


}
