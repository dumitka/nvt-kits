package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.dto.DeskDTO;
import com.backend.springboot.dtoTransformation.DeskToDeskDTO;
import com.backend.springboot.model.Desk;
import com.backend.springboot.service.DeskService;

@RestController
@RequestMapping("api/desks")
public class DeskContoller {
	@Autowired
	private DeskService deskService;
	
	@Autowired
	private DeskToDeskDTO deskToDeskDTO;
	
	@PreAuthorize("hasRole('ROLE_WAITER')")
	@GetMapping("/desk/{id}")
	public ResponseEntity<DeskDTO> getDesk(@PathVariable Integer id) {
		DeskDTO deskDTO = null;
		
		Desk desk = deskService.findOne(id);
		if (desk != null) {
			deskDTO = deskToDeskDTO.convert(desk);
		}
		
		return new ResponseEntity<DeskDTO>(deskDTO, HttpStatus.OK);
	}
}
