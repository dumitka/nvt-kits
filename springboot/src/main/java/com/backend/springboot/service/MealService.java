package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.repository.MealPriceRepository;
import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.repository.MenuMealPriceRepository;
import com.backend.springboot.repository.MenuRepository;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.model.Restaurant;

@Component
@Primary
@Service
public class MealService {
	
	//repositories
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MealPriceRepository mealPriceRepository;
	
	@Autowired
	private MenuMealPriceRepository menuMealPriceRepository;
	
	
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
		Optional<Meal> found  = mealRepository.findById(id);
		
		if(found.get().getDeleted()) {
			return false;
		}
		
		found.get().setDeleted(true);
		mealRepository.save(found.get());
		
		
		//sad moras naci sve meal priceeve from current menu sa tim jelom ii menu mealpriceeve ii postaviti onda novi menu
		Optional<Menu> oldCurrent = menuRepository.findByCurrent();
		oldCurrent.get().setCurrent(false); //ne zaboraviti sacuvati!!!!
		List<MealPrice> listOfMealPrices = menuMealPriceRepository.findAllMealsPricesByMenuId(oldCurrent.get().getId());
		menuRepository.save(oldCurrent.get());
		
		
		Restaurant restaurant = Restaurant.builder().id(1).build();
		Menu newMenu = Menu.builder().restaurant(restaurant).current(true).dateOfValidation(LocalDateTime.now()).build();
		menuRepository.save(newMenu);
		
		Optional<Menu> current = menuRepository.findByCurrent();
		
		//here will go mp that need to be added in new current menu 
		for(MealPrice mp : listOfMealPrices) {
			if(mp.getMeal().getId() != id) {
				MenuMealPrice mmp = MenuMealPrice.builder().deleted(false).mealPrice(mp).menu(current.get()).build();
				menuMealPriceRepository.save(mmp);
			}else {
				mp.setDeleted(true);
				mealPriceRepository.save(mp);
			}
		}
		
		return true;
	}
	
	
	
	public List<Meal> getAllMeals(){
		return mealRepository.findAll();
	}
	
}
