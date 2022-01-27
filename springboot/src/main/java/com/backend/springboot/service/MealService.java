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
	
	
	
	
	public Meal getMeal(Integer id) {
		Optional<Meal> meal = mealRepository.findById(id);
		return meal.get();
	}
	
	
	
	public boolean addMeal(Meal meal) {
		mealRepository.save(meal);
		return true;
	}
	
	
	
	public boolean changeMeal(Meal meal) {
		Optional<Meal> current  = mealRepository.findById(meal.getId());
		
		//check if meal with same name and same description already exists
		Optional<Meal> sameMeal = mealRepository.findMealByNameAndDescription(meal.getName(), meal.getDescription());
		
		if(!sameMeal.isEmpty()) {
			System.out.println("TACA");
			System.out.print("SAME MEAL --> " + sameMeal.get().getId());
			System.out.print("MEAL --> " + meal.getId());
			if(sameMeal.get().getId() != meal.getId()) {
				System.out.print("VRACA RETURN" + meal.getId());
				return false;
			}
		}
		
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
		System.out.println("TACA");
		System.out.println(id);
		
		Optional<Meal> found  = mealRepository.findById(id);
		
		System.out.println(found.get().getName() + "  id --> " + found.get().getId() + "    deleted -->" + found.get().getDeleted());
		;
		if(found.get().getDeleted()) {
			System.out.println("Uslo ovdje");
			return false;
		}
		
		found.get().setDeleted(true);
		mealRepository.save(found.get());
		return true;
	}
	
	
	
	public List<Meal> getAllMeals(){
		return mealRepository.findAll();
	}
	
}
