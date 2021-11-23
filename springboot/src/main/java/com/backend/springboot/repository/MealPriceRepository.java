package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.MealPrice;

public interface MealPriceRepository extends JpaRepository<MealPrice, Integer>{
}
