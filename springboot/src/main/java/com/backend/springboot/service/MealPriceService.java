package com.backend.springboot.service;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.repository.MealPriceRepository;
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
	
	
	
	public List<MealPrice> getAllMealPricebyMealType(MealType type){
		return mealPriceRepository.findAllMealPricebyMealType(type);
	}
  
	
	
	public boolean addMealPrice(MealPrice mealPrice) {
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		
		if(!currentMenu.isEmpty()) {
			Optional<MenuMealPrice> found = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(mealPrice.getId(), currentMenu.get().getId());
			if(!found.isEmpty()) {
				MenuMealPrice menuMealPrice = MenuMealPrice.builder().mealPrice(mealPrice).menu(currentMenu.get()).build();
				menuMealPriceRepository.save(menuMealPrice);
				return true;
			}
		}
		return false;
	}
	
	
	
	public boolean changeMealPrice(MealPrice mealPrice) {
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		if(!currentMenu.isEmpty()) {
			Optional<MealPrice> mealPriceInRepository = mealPriceRepository.findMealPriceById(mealPrice.getId());
			if(!mealPriceInRepository.isEmpty()) {
				mealPriceInRepository.get().setPriceAmount(mealPrice.getPriceAmount());
				mealPriceRepository.save(mealPriceInRepository.get());
				return true;
			}
		}
		return false;
	}
  
	
	
	
	public boolean deleteMealPriceFromMenu(MealPrice mealPrice) {
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		if(!currentMenu.isEmpty()) {
			Optional<MenuMealPrice>menuMealPrice = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(mealPrice.getId(), currentMenu.get().getId());
			if(!menuMealPrice.isEmpty()) {
				menuMealPriceRepository.delete(menuMealPrice.get());
				return true;
			}
		}
		return false;
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
