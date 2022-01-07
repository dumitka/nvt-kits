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
	
	//repositories
	@Autowired
	private MealRepository mealRepository;
	
	
	
	//methods
	public boolean exists(Integer id) {
		Optional<Meal> found = mealRepository.findById(id);
		if(found.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
	public boolean findByNameAndDescription(String name, String description) {
		Optional<Meal> found = mealRepository.findMealByNameAndDescription(name, description);
		if(found.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
	public List<Meal> getAllMealbyMealType(MealType type){
		return mealRepository.findMealbyMealType(type);
	}
	
	
	
	public boolean addMeal(Meal meal) {
		mealRepository.save(meal);
		return true;
	}
	
	
	
	public boolean changeMeal(Meal meal) {
		Optional<Meal> current  = mealRepository.findById(meal.getId());
		
		if(!current.isEmpty()) {
			current.get().setType(meal.getType());
			current.get().setDescription(meal.getDescription());
			current.get().setAmountNumber(meal.getAmountNumber());
			current.get().setAmountUnit(meal.getAmountUnit());
			current.get().setImage(meal.getImage());
			current.get().setMealDifficulty(meal.getMealDifficulty());
			current.get().setTimePreparation(meal.getTimePreparation());
			mealRepository.save(current.get());
			return true;
			
		}else {
			return false;
		}
	}
	
	
	
	public boolean delete(Integer id) {
		Optional<Meal> found  = mealRepository.findById(id);
		
		if(found.get().getDeleted()) {
			return false;
		}
		
		found.get().setDeleted(true);
		mealRepository.save(found.get());
		return true;
	}
	
	
}
