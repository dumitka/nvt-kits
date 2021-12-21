package com.backend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.dto.MenuDTO;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.service.MenuService;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	
	@Autowired
	private MenuService service;
	
	/*
	@GetMapping(value = "/getMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<MenuDTO> getMenu(){
		Menu current = this.service.getCurrentMenu();
	//	MenuDTO menu = new MenuDTO(current.getId(), current.getDateOfValidation(), current.getMealPrices());
     //   return new ResponseEntity<>(menu, HttpStatus.OK);
	}
	*/
	
	/*
	@PostMapping(value = "/addMealToMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<String> addMealToMenu(@RequestBody MealPrice meal) {
		service.addMeal(meal);
		return new ResponseEntity<String>("Added meal sucesfully.", HttpStatus.OK);
	}*/
	
	/*
	@PutMapping(value = "/changeMealPriceInMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<String> changeMealPriceInMenu(@RequestBody MealPrice mealprice) {
		service.changeMealPrice(mealprice);
		return new ResponseEntity<String>("Price has been changed sucesfully.", HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "/deleteMealInMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<String> deleteMealInMenu(@RequestBody MealPrice mealprice) {
		service.deleteMealPrice(mealprice);
		return new ResponseEntity<String>("Meal is deleted sucesfully.", HttpStatus.OK);
	}
	*/
	
	/*
	@PostMapping(value = "/newMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<String> newMenu(@RequestBody Menu menu) {
		service.addNewMenu(menu);
		return new ResponseEntity<String>("Added new menu sucesfully.", HttpStatus.OK);
	}
	
	*/
	
}
