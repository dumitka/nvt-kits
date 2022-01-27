package com.backend.springboot.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.MealPriceRepository;
import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.repository.MenuMealPriceRepository;
import com.backend.springboot.repository.MenuRepository;

@Component
@Primary
@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private MealPriceRepository mealPriceRepository;
	
	@Autowired
	private MenuMealPriceRepository menuMealPriceRepository;
	
	public Menu getCurrentMenu() {
		Optional<Menu> current = menuRepository.findByCurrent();
		if(current.isEmpty()) {
			return null;
		}else {
			return current.get();
		}
	}
	
	public Float getLatestForMeal(Integer mealId) {
        Menu current = getCurrentMenu();
        for (MenuMealPrice menuMealPrice : current.getMenuMealPrices()) {
			if (menuMealPrice.getMealPrice().getMeal().getId() == mealId) {
				return menuMealPrice.getMealPrice().getPrice();
			}
		}
        return null;
    }
	
	
	
	public boolean addNewMenu(List<MealWithPriceDTO> list) {
		Optional<Menu> old = menuRepository.findByCurrent();
		old.get().setCurrent(false);
		menuRepository.save(old.get());
		
		Restaurant restaurant = Restaurant.builder().id(1).build();
		Menu newMenu = Menu.builder().restaurant(restaurant).current(true).dateOfValidation(LocalDateTime.now()).build();
		menuRepository.save(newMenu);
		
		Optional<Menu> current = menuRepository.findByCurrent();
		
		for(MealWithPriceDTO m : list) {
			Optional<Meal> meal = mealRepository.findById(m.getMealDTO().getId());
			MealPrice mp = MealPrice.builder().meal(meal.get()).price(m.getPrice()).deleted(false).build();
			mealPriceRepository.save(mp);
			
			List<MealPrice> all = mealPriceRepository.findAll();
			MealPrice lastmp = all.get(all.size()-1);
			
			MenuMealPrice mmp = MenuMealPrice.builder().menu(current.get()).mealPrice(lastmp).deleted(false).build();
			menuMealPriceRepository.save(mmp);
		}
		
		return true;
	}
	
	
}
