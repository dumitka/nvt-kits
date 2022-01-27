package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

import com.backend.springboot.dto.MealDTO;
import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.dto.MenuDTO;
import com.backend.springboot.dtoTransformation.MealPriceToMealWithPriceDTO;
import com.backend.springboot.dtoTransformation.MealToMealDTO;
import com.backend.springboot.dtoTransformation.MenuMealPriceToMenuMealPriceDTO;
import com.backend.springboot.dtoTransformation.MenuToMenuDTO;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MenuService;
import com.backend.springboot.model.MenuMealPrice;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MealPriceService mealPriceService;
	
	private MenuToMenuDTO menuDTO;
	private MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO;
	private MealToMealDTO mealToMealDTO;
	
	
	public MenuController() {
		this.mealPriceToMealWithPriceDTO = new MealPriceToMealWithPriceDTO(new MealToMealDTO());
		this.menuDTO = new MenuToMenuDTO(new MenuMealPriceToMenuMealPriceDTO(new MealPriceToMealWithPriceDTO(new MealToMealDTO())));
		this.mealToMealDTO = new MealToMealDTO();
	}
	
	
	
	@GetMapping(value = "/getMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<MenuDTO> getMenu(){
		Menu current = this.menuService.getCurrentMenu();
		if(current != null) {
			MenuDTO dto = menuDTO.convert(current);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		else {
			Menu menu = Menu.builder().id(0).dateOfValidation(LocalDateTime.now()).current(false)
					.restaurant(Restaurant.builder().id(1).build())
					.build();
			Set<MenuMealPrice> set = Collections.<MenuMealPrice>emptySet();
			menu.setMenuMealPrices(set);
			MenuDTO dto = menuDTO.convert(menu);
			return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	@PostMapping(value = "/addMealToMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> addMealToMenu(@RequestBody MealWithPriceDTO mealPriceDTO) {
		Meal meal = Meal.builder().id(mealPriceDTO.getMealDTO().getId()).amountNumber(mealPriceDTO.getMealDTO().getAmountNumber())
				.amountUnit(mealPriceDTO.getMealDTO().getAmountUnit()).deleted(mealPriceDTO.getMealDTO().getDeleted())
				.description(mealPriceDTO.getMealDTO().getDescription()).image(mealPriceDTO.getMealDTO().getImage())
				.mealDifficulty(mealPriceDTO.getMealDTO().getMealDifficulty()).name(mealPriceDTO.getMealDTO().getName()).
				timePreparation(mealPriceDTO.getMealDTO().getTimePreparation()).type(mealPriceDTO.getMealDTO().getType()).build();
		MealPrice mealPrice = MealPrice.builder().meal(meal).price(mealPriceDTO.getPrice()).deleted(false).build();
		
		mealPriceService.addMealPrice(mealPrice);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping(value = "/changeMealPriceInMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> changeMealPriceInMenu(@RequestBody MealWithPriceDTO mealPriceDTO) {
		boolean found = mealPriceService.exists(mealPriceDTO.getId());
		if(!found) {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
		}	
		MealPrice mealPrice = mealPriceService.getMealPrice(mealPriceDTO.getId());
		mealPrice.setPrice(mealPriceDTO.getPrice());
		
		Boolean  returnValue = mealPriceService.changeMealPrice(mealPrice);
		return new ResponseEntity<>(returnValue, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/deleteMealInMenu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> deleteMealInMenu(@RequestBody MealWithPriceDTO mealPriceDTO)throws Exception {
		boolean found = mealPriceService.exists(mealPriceDTO.getId());
		if(!found) {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
		}
		Boolean response = mealPriceService.deleteMealPriceFromMenu(mealPriceDTO.getId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value = "/newMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> newMenu(@RequestBody Menu menu) {
		menuService.addNewMenu(menu);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/getMealPricesNotInMenu")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getMealPricesNotInMenu() throws Exception {
		Menu current = this.menuService.getCurrentMenu();
		if(current != null) {
			List<Meal> list = mealPriceService.getMealPricesThatAreNotInMenu(current.getId());
			List<MealDTO> dto = this.mealToMealDTO.convertList(list);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}else {
			List<MealDTO> list = Collections.emptyList();
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
	
	}
	
	
}
