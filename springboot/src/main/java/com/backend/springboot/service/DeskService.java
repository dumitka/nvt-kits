package com.backend.springboot.service;

import com.backend.springboot.model.Desk;
import com.backend.springboot.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeskService {
	@Autowired
	private DeskRepository deskRepository;

	public List<Desk> findAll() {
		return deskRepository.findByDeletedFalse();
	}

	public Desk findOne(Integer id) {
		return deskRepository.findOneByIdAndDeletedFalse(id);
	}

	public Desk save(Desk desk) {
		return deskRepository.save(desk);
	}

	public void delete(Desk desk) {
		Desk found = deskRepository.findOneByIdAndDeletedFalse(desk.getId());
		found.setDeleted(true);
		deskRepository.save(found);
	}

	public void deleteAll() {
		List<Desk> desks = deskRepository.findAll();
		desks.forEach(desk -> {
			desk.setDeleted(true);
			deskRepository.save(desk);
		});
	}
}
