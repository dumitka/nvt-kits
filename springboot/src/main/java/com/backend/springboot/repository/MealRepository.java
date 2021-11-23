package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer>{

}
