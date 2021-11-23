package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.DrinkCard;

public interface DrinkCardRepository extends JpaRepository<DrinkCard, Integer>{
}
