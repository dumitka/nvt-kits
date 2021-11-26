package com.backend.springboot.repository;

import com.backend.springboot.model.MealPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPriceRepository extends JpaRepository<MealPrice, Integer> {

    MealPrice findOneByMealId(Integer mealId);
}
