package com.backend.springboot.repository;

import com.backend.springboot.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk, Integer>{
	public Desk findOneById(Integer id);
}
