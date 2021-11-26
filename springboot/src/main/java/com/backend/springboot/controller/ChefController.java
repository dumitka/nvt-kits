package com.backend.springboot.controller;

import com.backend.springboot.dto.UserProfileDataDTO;
import com.backend.springboot.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chef")
public class ChefController {

	@GetMapping(value = "/get")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<UserProfileDataDTO> getChef() {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserProfileDataDTO dto = new UserProfileDataDTO(loggedUser.getId(), loggedUser.getName(), loggedUser.getLastName());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
