package com.backend.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.repository.MealPriceRepository;
import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.repository.MenuRepository;

@Component
@Primary
@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MealPriceRepository mealPriceRepository;
	
	public Menu getCurrentMenu() {
		List<Menu> menus = menuRepository.findAll();
		int index = menus.size();
		return menus.get(index-1);
	}
	
	
	public boolean addMeal(MealPrice meal) {
		Menu current = getCurrentMenu();
		meal.getMenus().add(current);
		current.getMealPrices().add(meal);
		mealPriceRepository.save(meal);
		menuRepository.save(current);
		return true;
	}
	
	
	/*Iterator<String> it = hset.iterator();
     while(it.hasNext()){
        System.out.println(it.next());
     }*/
	
	
	public boolean changeMealPrice(MealPrice mealPrice) {
		Menu current = getCurrentMenu();
		
		Iterator<MealPrice> it = current.getMealPrices().iterator();
		while(it.hasNext()) {
			if(it.next().getId() == mealPrice.getId()) {
				//pronadjeni mealPrice
				it.next().setPriceAmount(mealPrice.getPriceAmount());
			}
		}
		//sacuvan izmjenjen u mealPriceRepository
		MealPrice mealInRepository = mealPriceRepository.findMealPriceById(mealPrice.getId());
		mealInRepository.setPriceAmount(mealPrice.getPriceAmount());
		mealPriceRepository.save(mealInRepository);
		
		//sacuvan izmjenjen u meniju
		menuRepository.save(current);
		return true;
	}
	
	
	
	public boolean deleteMealPrice(MealPrice mealPrice) {
		Menu current = getCurrentMenu();
		
		Iterator<MealPrice> it = current.getMealPrices().iterator();
		while(it.hasNext()) {
			if(it.next().getId() == mealPrice.getId()) {
				//pronadjeni mealPrice
				current.getMealPrices().remove(it.next());
			}
		}
		
		//sacuvan izmjene u meniju
		menuRepository.save(current);
		return true;
	}
	
	
	
	public boolean addNewMenu(Menu menu) {
		Menu current = getCurrentMenu();
		menu.setDateOfValidation(LocalDateTime.now());
		menu.setRestaurant(current.getRestaurant());
		menuRepository.save(menu);
		return true;
	}
}
