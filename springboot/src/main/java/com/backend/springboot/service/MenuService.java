package com.backend.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.repository.MenuMealPriceRepository;
import com.backend.springboot.repository.MenuRepository;

@Component
@Primary
@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	
	public Menu getCurrentMenu() {
		Optional<Menu> current = menuRepository.findByCurrent();
		if(current.isEmpty()) {
			return null;
		}else {
			return current.get();
		}
	}
	
	
	public boolean addNewMenu(Menu menu) {
		menuRepository.save(menu);
		return true;
	}
	
	
}
