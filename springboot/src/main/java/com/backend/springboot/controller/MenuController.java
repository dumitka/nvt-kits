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
import com.backend.springboot.dtoTransformation.MealPriceToMealWithPriceDTO;
import com.backend.springboot.dtoTransformation.MenuToMenuDTO;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MenuService;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	
	@Autowired
	private MenuService service;
	
	@Autowired
	private MealPriceService mealPriceService;
	
	private MenuToMenuDTO menuDTO;
	private MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO;
	
	@GetMapping(value = "/getMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<MenuDTO> getMenu() throws Exception{
		Menu current = this.service.getCurrentMenu();
		if(current != null) {
			MenuDTO dto = menuDTO.convert(current);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}
	
	
	
	@PostMapping(value = "/addMealToMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> addMealToMenu(@RequestBody MealPrice meal)throws Exception {
		Boolean response =  mealPriceService.addMealPrice(meal);
		if(response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PutMapping(value = "/changeMealPriceInMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> changeMealPriceInMenu(@RequestBody MealPrice mealprice) throws Exception {
		Boolean response =  mealPriceService.changeMealPrice(mealprice);
		if(response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@DeleteMapping(value = "/deleteMealInMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> deleteMealInMenu(@RequestBody MealPrice mealprice)throws Exception {
		Boolean response =  mealPriceService.deleteMealPriceFromMenu(mealprice);
		if(response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PostMapping(value = "/newMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> newMenu(@RequestBody Menu menu) {
		Boolean response = service.addNewMenu(menu);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/getMealPricesNotInMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getMealPricesNotInMenu() throws Exception {
		Menu current = this.service.getCurrentMenu();
		if(current != null) {
			List<MealPrice> list = mealPriceService.getMealPricesThatAreNotInMenu(current.getId());
			return new ResponseEntity<>(mealPriceToMealWithPriceDTO.convertList(list), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}
	
	
}
