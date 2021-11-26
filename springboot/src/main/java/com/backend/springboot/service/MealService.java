package com.backend.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;

@Component
@Primary
@Service
public class MealService {
	
	@Autowired
	private MealRepository mealRepository;
	
	
	public List<Meal> getAllMealbyMealType(MealType type){
		return mealRepository.findMealbyMealType(type);
	}
	
	
	public boolean addMeal(Meal meal) {
		mealRepository.save(meal);
		return true;
	}
	
	public boolean changeMeal(Meal meal) {
		Meal current = mealRepository.findMealById(meal.getId());
		current.setDescription(meal.getDescription());
		current.setAmountNumber(meal.getAmountNumber());
		current.setAmountUnit(meal.getAmountUnit());
		current.setImage(meal.getImage());
		current.setMealDifficulty(meal.getMealDifficulty());
		current.setName(meal.getName());
		current.setTimePreparation(meal.getTimePreparation());
		current.setType(meal.getType());
		mealRepository.save(current);
		return true;
	}
	
	
	
	public boolean delete(Meal meal) {
		Meal current = mealRepository.findMealById(meal.getId());
		current.setDeleted(true);
		mealRepository.save(current);
		return true;
	}
	
	
}
