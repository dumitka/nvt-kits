package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Integer>{

}
