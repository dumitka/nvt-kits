package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.springboot.model.Desk;
import com.backend.springboot.repository.DeskRepository;

public class DeskService {
	@Autowired
	private DeskRepository deskRepository;
	
	public Desk findOne(Integer id) {
		return deskRepository.findOneById(id);
	}
	
	public Desk save(Desk desk) {
		return deskRepository.save(desk);
	}
}
