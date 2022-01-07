package com.backend.springboot.repository;


import java.util.List;
import java.util.Optional;

import com.backend.springboot.model.MealPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.backend.springboot.enums.MealType;


public interface MealPriceRepository extends JpaRepository<MealPrice, Integer>{
	
	@Query("select m from MealPrice m where m.meal.type = ?1 and m.deleted = false")
	List<MealPrice> findAllMealPricebyMealType(MealType mealTypeParam);
	
	@Query("select m from MealPrice m where m.id = ?1 and m.deleted = false")
	Optional<MealPrice> findMealPriceById(Integer id);
  
	
	Optional<MealPrice> findOneByMealId(Integer mealId);
	
	
}
