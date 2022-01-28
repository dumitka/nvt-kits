package com.backend.springboot.repository;

import com.backend.springboot.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeskRepository extends JpaRepository<Desk, Integer>{

	Desk findOneByIdAndDeletedFalse(Integer id);

	List<Desk> findByDeletedFalse();

}
