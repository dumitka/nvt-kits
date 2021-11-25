package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.model.User;
import com.backend.springboot.util.TokenUtils;




//kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername().toLowerCase(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			User user = (User) authentication.getPrincipal();
			String jwt = tokenUtils.generateToken(user.getUsername());
			int expiresIn = tokenUtils.getExpiredIn();
			
			return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user.getId(), user.getUsername(), user.getRoles().get(0).getName()));
		} catch (DisabledException de) {
			return new ResponseEntity<UserTokenState>(HttpStatus.FORBIDDEN);
		} catch (BadCredentialsException bdc) {
			return new ResponseEntity<UserTokenState>(HttpStatus.NOT_FOUND);
		}
	}
	
}
