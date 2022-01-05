package com.backend.springboot.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.exception.CurrentMenuNotFoundException;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.MenuRepository;

@Component
@Primary
@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	
	public Menu getCurrentMenu() throws Exception {
		Optional<Menu> current = menuRepository.findByCurrent();
		if(current.isEmpty()) {
			throw new CurrentMenuNotFoundException("Current menu not found.");
		}else {
			return current.get();
		}
	}
	
	
	public boolean addNewMenu(Menu menu) {
		Restaurant restaurant = Restaurant.builder().id(1).build();
		menu.setRestaurant(restaurant);
		menuRepository.save(menu);
		return true;
	}
	
	
	@Scheduled(fixedDelayString = "PT24H")
	public void SettingCurrentMenu() throws InterruptedException {
		LocalDateTime now = LocalDateTime.now();
		Optional<Menu> newMenu = menuRepository.findBydateTime(now);
		
		if(!newMenu.isEmpty()) {
			Optional<Menu> current = menuRepository.findByCurrent();
			current.get().setCurrent(false);
			newMenu.get().setCurrent(true);
			menuRepository.save(current.get());
			menuRepository.save(newMenu.get());
		}
		
		
		
	}
}
