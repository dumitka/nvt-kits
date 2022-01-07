 package com.backend.springboot.service;


import java.util.ArrayList;
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
	
	
	
	public List<MealPrice> getAllMealPricebyMealType(MealType type){
		return mealPriceRepository.findAllMealPricebyMealType(type);
	}
  
	
	
	public boolean addMealPrice(MealPrice mealPrice){
		mealPrice.setDeleted(false);
		mealPriceRepository.save(mealPrice);
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		MenuMealPrice menuMealPrice = MenuMealPrice.builder().mealPrice(mealPrice).menu(currentMenu.get()).deleted(false).build();
		menuMealPriceRepository.save(menuMealPrice);
		menuRepository.save(currentMenu.get());
		return true;
	}
	
	
	
	public boolean changeMealPrice(MealPrice mealPrice) {
		Optional<MealPrice> found = mealPriceRepository.findById(mealPrice.getId());
		found.get().setPriceAmount(mealPrice.getPriceAmount());
		mealPriceRepository.save(found.get());
		return true;
	}
  
	
	
	
	public boolean deleteMealPriceFromMenu(Integer id) {
		Optional<Menu>currentMenu = menuRepository.findByCurrent();
		Optional<MenuMealPrice>menuMealPrice = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(id, currentMenu.get().getId());
		Optional<MealPrice> foundMealPrice = mealPriceRepository.findById(id);
		
		foundMealPrice.get().setDeleted(true);
		menuMealPrice.get().setDeleted(true);
		mealPriceRepository.save(foundMealPrice.get());
		menuMealPriceRepository.save(menuMealPrice.get());
		menuRepository.save(currentMenu.get());
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
