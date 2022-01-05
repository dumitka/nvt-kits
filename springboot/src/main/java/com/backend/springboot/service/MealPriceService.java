 package com.backend.springboot.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.enums.MealType;
import com.backend.springboot.exception.CurrentMenuNotFoundException;
import com.backend.springboot.exception.MealPriceAlreadyExistsException;
import com.backend.springboot.exception.MealPriceNotFoundException;
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
  
	
	
	public boolean addMealPrice(MealPrice mealPrice) throws Exception{
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		if(!currentMenu.isEmpty()) {
			mealPriceRepository.save(mealPrice);
			Optional<MenuMealPrice> found = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(mealPrice.getId(), currentMenu.get().getId());
			//if mealPrice does not exists in menu, create it and add it.
			if(found.isEmpty()) {
				MenuMealPrice menuMealPrice = MenuMealPrice.builder().mealPrice(mealPrice).menu(currentMenu.get()).deleted(false).build();
				menuMealPriceRepository.save(menuMealPrice);
				menuRepository.save(currentMenu.get());
				return true;
			
			}else {
				throw new MealPriceAlreadyExistsException("MealPrice already exists.");
			}
			
		}else {
			throw new CurrentMenuNotFoundException("Current menu not found.");
		}
	}
	
	
	
	public boolean changeMealPrice(MealPrice mealPrice)throws Exception {
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		if(!currentMenu.isEmpty()) {
			Optional<MealPrice> mealPriceInRepository = mealPriceRepository.findMealPriceById(mealPrice.getId());
			if(!mealPriceInRepository.isEmpty()) {
				mealPriceInRepository.get().setPriceAmount(mealPrice.getPriceAmount());
				mealPriceRepository.save(mealPriceInRepository.get());
				return true;
			}else {
				throw new MealPriceNotFoundException("MealPrice is not found.");
			}
		}else {
			throw new CurrentMenuNotFoundException("Current menu not found.");
		}
	}
  
	
	
	
	public boolean deleteMealPriceFromMenu(MealPrice mealPrice) throws Exception {
		Optional<Menu> currentMenu = menuRepository.findByCurrent();
		if(!currentMenu.isEmpty()) {
			Optional<MenuMealPrice>menuMealPrice = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(mealPrice.getId(), currentMenu.get().getId());
			Optional<MealPrice> foundMealPrice = mealPriceRepository.findById(mealPrice.getId());
			if(!menuMealPrice.isEmpty()) {
				foundMealPrice.get().setDeleted(true);
				menuMealPrice.get().setDeleted(true);
				mealPriceRepository.save(foundMealPrice.get());
				menuMealPriceRepository.save(menuMealPrice.get());
				return true;
			}else {
				throw new MealPriceNotFoundException("MealPrice is not found.");
			}
		}else {
			throw new CurrentMenuNotFoundException("Current menu not found.");
		}
		
	}
	
	
	public List<MealPrice> getMealPricesThatAreNotInMenu(Integer menuId){
		List<MealPrice> allMealPrices = mealPriceRepository.findAll();
		List<MealPrice> allMealPricesInMenu = menuMealPriceRepository.findAllMealsPricesByMenuId(menuId);
		
		List<MealPrice> returnList = new ArrayList<MealPrice>();
		
		for(int i = 0; i<allMealPrices.size(); i++) {
			boolean flag = false;
			for(int j = 0; j<allMealPricesInMenu.size(); j++) {
				if(allMealPrices.get(i).getId() == allMealPricesInMenu.get(j).getId()) {
					flag = true; //contains
				}
			}
			if(flag == false) {
				returnList.add(allMealPrices.get(i));
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
