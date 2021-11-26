package com.backend.springboot.service;

import com.backend.springboot.model.Desk;
import com.backend.springboot.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
