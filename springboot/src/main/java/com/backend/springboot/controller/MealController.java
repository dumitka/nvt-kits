package com.backend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.backend.springboot.dtoTransformation.MealPriceToMealWithPriceDTO;
import com.backend.springboot.dtoTransformation.MealToMealDTO;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Meal;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MealService;



@RestController
@RequestMapping(value = "/meal")
public class MealController {
	
	@Autowired
	private MealPriceService service;
	
	@Autowired
	private MealService service2;
	
	private MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO;
	
	public MealController() {this.mealPriceToMealWithPriceDTO = new MealPriceToMealWithPriceDTO(new MealToMealDTO());}
	
	@GetMapping(value = "/getColdAppetizers")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getColdAppetizers(){
		List<MealPrice> meals = service.getAllMealPricebyMealType(MealType.COLD_APPETIZER);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getHotAppetizer")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getHotAppetizer(){
		List<MealPrice> meals = service.getAllMealPricebyMealType(MealType.HOT_APPETIZER);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMainCourse")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getMainCourse(){
		List<MealPrice> meals = service.getAllMealPricebyMealType(MealType.MAIN_COURSE);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getDesert")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getDesert(){
		List<MealPrice> meals = service.getAllMealPricebyMealType(MealType.DESERT);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSalad")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getSalad(){
		List<MealPrice> meals = service.getAllMealPricebyMealType(MealType.SALAD);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAppendices")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getAppendices(){
		List<MealPrice> meals = service.getAllMealPricebyMealType(MealType.APPENDICES);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/addMeal")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> addMeal(@RequestBody MealDTO mealDTO) throws Exception  {
		
		Meal meal = Meal.builder().name(mealDTO.getName()).description(mealDTO.getDescription())
				.amountNumber(mealDTO.getAmountNumber()).amountUnit(mealDTO.getAmountUnit()).deleted(mealDTO.getDeleted())
				.image(mealDTO.getImage()).mealDifficulty(mealDTO.getMealDifficulty()).timePreparation(mealDTO.getTimePreparation())
				.type(mealDTO.getType()).build();
		
		boolean response = service2.addMeal(meal);
		if(!response) {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/changeMeal")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> changeMeal(@RequestBody MealDTO mealDTO) throws Exception  {
		
		Meal meal = Meal.builder().id(mealDTO.getId()).name(mealDTO.getName()).description(mealDTO.getDescription())
				.amountNumber(mealDTO.getAmountNumber()).amountUnit(mealDTO.getAmountUnit()).deleted(mealDTO.getDeleted())
				.image(mealDTO.getImage()).mealDifficulty(mealDTO.getMealDifficulty()).timePreparation(mealDTO.getTimePreparation())
				.type(mealDTO.getType()).build();
		
		boolean response = service2.changeMeal(meal);
		if(!response) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/deleteMeal")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> deleteMeal(@RequestBody MealDTO mealDTO) throws Exception{
		Meal meal = Meal.builder().id(mealDTO.getId()).name(mealDTO.getName()).description(mealDTO.getDescription())
				.amountNumber(mealDTO.getAmountNumber()).amountUnit(mealDTO.getAmountUnit()).deleted(mealDTO.getDeleted())
				.image(mealDTO.getImage()).mealDifficulty(mealDTO.getMealDifficulty()).timePreparation(mealDTO.getTimePreparation())
				.type(mealDTO.getType()).build();
		
		boolean response = service2.delete(meal);
		if(!response) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
}
