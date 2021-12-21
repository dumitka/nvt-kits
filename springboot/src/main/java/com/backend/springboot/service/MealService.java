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
		//check if exists
		Optional<Meal> found = mealRepository.findMealByNameAndDescription(meal.getName(), meal.getDescription());
		
		if(found.isEmpty()) {
			//if doesn't, save
			mealRepository.save(meal);
			return true;
		}else {
			//if does, don't do anything
			return false;
		}
		
	}
	
	
	
	public boolean changeMeal(Meal meal) {
		Optional<Meal> current  = mealRepository.findById(meal.getId());
		
		if(!current.isEmpty()) {
			current.get().setDescription(meal.getDescription());
			current.get().setAmountNumber(meal.getAmountNumber());
			current.get().setAmountUnit(meal.getAmountUnit());
			current.get().setImage(meal.getImage());
			current.get().setMealDifficulty(meal.getMealDifficulty());
			current.get().setName(meal.getName());
			current.get().setTimePreparation(meal.getTimePreparation());
			current.get().setType(meal.getType());
			mealRepository.save(current.get());
			return true;
		}
		
		//it should not come to this line
				return false;
		
	}
	
	
	
	public boolean delete(Meal meal) {
		Optional<Meal> current = mealRepository.findMealById(meal.getId());
		if(!current.isEmpty()) {
			current.get().setDeleted(true);
			mealRepository.save(current.get());
			return true;
		}
		
		//it should not come to this line
			return false;
	}
	
	
}
