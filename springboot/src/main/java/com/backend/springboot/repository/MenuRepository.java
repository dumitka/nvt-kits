package com.backend.springboot.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;


public interface MenuRepository extends JpaRepository<Menu, Integer>{
	
	@Query("select m from Menu m where m.current = true")
	Optional<Menu> findByCurrent();
	
	
}
