package com.backend.springboot.repository;


import java.util.List;


import com.backend.springboot.model.MealPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.MealPrice;

public interface MealPriceRepository extends JpaRepository<MealPrice, Integer>{
	@Query("select m from MealPrice m where m.meal.type = ?1")
	List<MealPrice> findAllMealPricebyMealType(MealType mealTypeParam);
	
	@Query("select m from MealPrice m where m.id = ?1")
	MealPrice findMealPriceById(Integer id);
  
	
	MealPrice findOneByMealId(Integer mealId);

}
