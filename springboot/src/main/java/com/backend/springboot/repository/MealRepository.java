package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer>{

	@Query("select m from Meal m where m.type = ?1 and m.deleted = false")
	List<Meal> findMealbyMealType(MealType mealTypeParam);
	
	@Query("select m from Meal m where m.id = ?1")
	Meal findMealById(Integer id);
}
