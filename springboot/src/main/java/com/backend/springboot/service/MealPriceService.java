 package com.backend.springboot.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.MealPriceRepository;
import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.repository.MenuMealPriceRepository;
import com.backend.springboot.repository.MenuRepository;

import java.time.LocalDateTime;


@Component
@Primary
@Service
public class MealPriceService {
	
	@Autowired
	private MealPriceRepository mealPriceRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuMealPriceRepository menuMealPriceRepository;
	
	@Autowired
	private MealRepository mealRepository;
	
	public boolean exists(Integer id) {
		Optional<MealPrice> found = mealPriceRepository.findById(id);
		if(found.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
		
	
	public MealPrice getMealPrice(Integer id) {
		Optional<MealPrice> found = mealPriceRepository.findById(id);
		return found.get();
	}
		
	
	
	public List<MealPrice> getAllMealPricebyMealType(MealType type){
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		if(currentMenu.isPresent()) {
			return menuMealPriceRepository.findAllMealsPricesByMenuIdAndMealType(currentMenu.get().getId(), type);
		}
		return new ArrayList<MealPrice>();
	}
  
	
	
	public boolean addMealPrice(MealPrice mealPrice){
		mealPrice.setDeleted(false);
		mealPriceRepository.save(mealPrice);
		
		Optional<Menu>oldCurrent = menuRepository.findByCurrent();
		Integer oldCurrentId = oldCurrent.get().getId();
		oldCurrent.get().setCurrent(false);
		menuRepository.save(oldCurrent.get());
		
		Restaurant restaurant = Restaurant.builder().id(1).build();
		Menu newMenu = Menu.builder().restaurant(restaurant).current(true).dateOfValidation(LocalDateTime.now()).build();
		menuRepository.save(newMenu);
		
		Optional<Menu> newCurrent = menuRepository.findByCurrent();
		
		List<MealPrice> allMealPricesInOldMenu = menuMealPriceRepository.findAllMealsPricesByMenuId(oldCurrentId);
		for(MealPrice m : allMealPricesInOldMenu) {
			MenuMealPrice mmp = MenuMealPrice.builder().menu(newCurrent.get()).mealPrice(m).deleted(false).build();
			menuMealPriceRepository.save(mmp);
		}
		
		List<MealPrice> all = mealPriceRepository.findAll();
		MealPrice lastOne = all.get(all.size()-1);
		MenuMealPrice mmp = MenuMealPrice.builder().menu(newCurrent.get()).mealPrice(lastOne).deleted(false).build();
		menuMealPriceRepository.save(mmp);
		
		return true;
	}
	
	
	
	
	
	public boolean changeMealPrice(MealPrice mealPrice) {
		MealPrice newMealPrice = MealPrice.builder().meal(mealPrice.getMeal()).deleted(false).price(mealPrice.getPrice()).build();
		mealPriceRepository.save(newMealPrice);
		
		Optional<Menu> oldMenu = menuRepository.findByCurrent();
		if(oldMenu.isEmpty()) {
			return false;
		}
		Integer idOfOldMenu = oldMenu.get().getId();
		oldMenu.get().setCurrent(false);
		Restaurant restaurant = Restaurant.builder().id(1).build();
		Menu newMenu = Menu.builder().restaurant(restaurant).current(true).dateOfValidation(LocalDateTime.now()).build();
		menuRepository.save(oldMenu.get());
		menuRepository.save(newMenu);
		
		Optional<Menu> newCurrent = menuRepository.findByCurrent();
		
		List<MealPrice> allMealPricesInOldMenu = menuMealPriceRepository.findAllMealsPricesByMenuId(idOfOldMenu);
		for(MealPrice m : allMealPricesInOldMenu) {
			
			if(m.getId() == mealPrice.getId()) {
				List<MealPrice> all = mealPriceRepository.findAll();
				MealPrice lastOne = all.get(all.size()-1);
				MenuMealPrice mmp = MenuMealPrice.builder().menu(newCurrent.get()).mealPrice(lastOne).deleted(false).build();
				menuMealPriceRepository.save(mmp);
			}else {
				MenuMealPrice mmp = MenuMealPrice.builder().menu(newCurrent.get()).mealPrice(m).deleted(false).build();
				menuMealPriceRepository.save(mmp);
			}
		}
		
		return true;
	}
  
	
	
	
	
	public boolean deleteMealPriceFromMenu(Integer id) {
		
		Optional<Menu>oldCurrent = menuRepository.findByCurrent();
		Integer oldCurrentId = oldCurrent.get().getId();
		oldCurrent.get().setCurrent(false);
		menuRepository.save(oldCurrent.get());
		
		Restaurant restaurant = Restaurant.builder().id(1).build();
		Menu newMenu = Menu.builder().restaurant(restaurant).current(true).dateOfValidation(LocalDateTime.now()).build();
		menuRepository.save(newMenu);
		
		Optional<Menu> newCurrent = menuRepository.findByCurrent();
		
		List<MealPrice> allMealPricesInOldMenu = menuMealPriceRepository.findAllMealsPricesByMenuId(oldCurrentId);
		for(MealPrice m : allMealPricesInOldMenu) {
			
			if(m.getId() == id) {
				MenuMealPrice mmp = MenuMealPrice.builder().menu(newCurrent.get()).mealPrice(m).deleted(true).build();
				menuMealPriceRepository.save(mmp);
			}else {
				MenuMealPrice mmp = MenuMealPrice.builder().menu(newCurrent.get()).mealPrice(m).deleted(false).build();
				menuMealPriceRepository.save(mmp);
			}
		}
		
		Optional<MealPrice> foundMealPrice = mealPriceRepository.findById(id);
		foundMealPrice.get().setDeleted(true);
		mealPriceRepository.save(foundMealPrice.get());
		
		return true;
	}
	
	
	
	
	
	public List<Meal> getMealPricesThatAreNotInMenu(Integer menuId){
		List<Meal> allMeals = mealRepository.findAll();
		List<MealPrice> allMealPricesInMenu = menuMealPriceRepository.findAllMealsPricesByMenuId(menuId);
		List<Meal> returnList = new ArrayList<Meal>();
		for(int i = 0; i<allMeals.size(); i++) {
			boolean flag = false;
			if(!allMeals.get(i).getDeleted()) {
				for(int j = 0; j<allMealPricesInMenu.size(); j++) {
					if(allMeals.get(i).getId() == allMealPricesInMenu.get(j).getMeal().getId()) {
						flag = true; //contains
					}
				}
				if(flag == false) {
					returnList.add(allMeals.get(i));
				}
			}
		}
		return returnList;
	}
	
	
	
	
	
	/*
	 *Izvinjavam se ciji sam kod dirala, htjela sam da bude u skladu sa ostalim klasama sto se tice jela
	 *Semantika je ista!
																								TACA :)
	*/
	public MealPrice findPriceOfMealForDate(LocalDateTime date, Integer mealId) { //todo date instead of datetime?
		Optional<MealPrice> found = mealPriceRepository.findOneByMealId(mealId);
		return found.get();
    }
	
}
