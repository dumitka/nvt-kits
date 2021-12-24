package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.springboot.model.Menu;


public interface MenuRepository extends JpaRepository<Menu, Integer>{
	
	@Query("select m from Menu m where m.current = true")
	Optional<Menu> findByCurrent();
	
	@Query("select m from Menu m where m.dateOfValidation = ?1")
	Optional<Menu> findBydateTime(LocalDateTime dateTime);
	
}
