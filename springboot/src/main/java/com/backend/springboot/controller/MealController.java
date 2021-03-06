package com.backend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MealService;



@RestController
@RequestMapping(value = "/meal")
public class MealController {
	
	//services
	@Autowired
	private MealPriceService mealPriceService;
	
	@Autowired
	private MealService mealService;
	
	
	//dto transformations
	private MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO;
	private MealToMealDTO mealToMealDTO;
	
	
	//constructor
	public MealController() {
		this.mealPriceToMealWithPriceDTO = new MealPriceToMealWithPriceDTO(new MealToMealDTO());
		this.mealToMealDTO = new MealToMealDTO();
	}
	
	
	
	@GetMapping(value = "/getColdAppetizers")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getColdAppetizers(){
		List<MealPrice> meals = mealPriceService.getAllMealPricebyMealType(MealType.COLD_APPETIZER);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getHotAppetizer")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getHotAppetizer(){
		List<MealPrice> meals = mealPriceService.getAllMealPricebyMealType(MealType.HOT_APPETIZER);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getMainCourse")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getMainCourse(){
		List<MealPrice> meals = mealPriceService.getAllMealPricebyMealType(MealType.MAIN_COURSE);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getDesert")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getDesert(){
		List<MealPrice> meals = mealPriceService.getAllMealPricebyMealType(MealType.DESERT);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getSalad")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getSalad(){
		List<MealPrice> meals = mealPriceService.getAllMealPricebyMealType(MealType.SALAD);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getAppendices")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealWithPriceDTO>> getAppendices(){
		List<MealPrice> meals = mealPriceService.getAllMealPricebyMealType(MealType.APPENDICES);
		List<MealWithPriceDTO> dto = this.mealPriceToMealWithPriceDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	

	
	@PostMapping(value = "/addMeal")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> addMeal(@RequestBody MealDTO mealDTO) {
		boolean found = this.mealService.findByNameAndDescription(mealDTO.getName(), mealDTO.getDescription());
		
		if(found) {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}

		
		Meal meal = Meal.builder().name(mealDTO.getName()).description(mealDTO.getDescription())
				.amountNumber(mealDTO.getAmountNumber()).amountUnit(mealDTO.getAmountUnit())
				.image(mealDTO.getImage()).mealDifficulty(mealDTO.getMealDifficulty()).timePreparation(mealDTO.getTimePreparation())
				.type(mealDTO.getType()).build();
		
		meal.setDeleted(false);
		
		boolean response = mealService.addMeal(meal);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/changeMeal")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> changeMeal(@RequestBody MealDTO mealDTO) {
		System.out.println("TACA");
		System.out.println(mealDTO.getId());
		
		boolean found  = mealService.exists(mealDTO.getId());
		System.out.println(found);
		
		if(!found) {
			System.out.println("Uslo u !found ");
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
		}
		
		System.out.println("Pravimo meal");

		Meal meal = Meal.builder().id(mealDTO.getId()).name(mealDTO.getName()).description(mealDTO.getDescription())
				.amountNumber(mealDTO.getAmountNumber()).amountUnit(mealDTO.getAmountUnit())
				.image(mealDTO.getImage()).mealDifficulty(mealDTO.getMealDifficulty()).timePreparation(mealDTO.getTimePreparation())
				.type(mealDTO.getType()).build();
		
		System.out.println("Treba pozvati change funkciju");
		boolean response = mealService.changeMeal(meal);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/deleteMeal")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<Boolean> deleteMeal(@RequestBody MealDTO mealDTO) {
		boolean found = this.mealService.exists(mealDTO.getId());
		if(!found) {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
		}

		boolean response = mealService.delete(mealDTO.getId());
		if(response) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			//it's already deleted!
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping(value = "getAllMeals")
	@PreAuthorize("hasAnyRole('ROLE_CHEF', 'ROLE_WAITER')")
	public ResponseEntity<List<MealDTO>> getAllMeals(){
		List<Meal> allMeals = this.mealService.getAllMeals();
		List<MealDTO> dto = this.mealToMealDTO.convertList(allMeals);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "getOne/id={id}")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<MealDTO> getOne(@PathVariable("id") Integer id){
		Meal meal = mealService.getMeal(id);
		MealDTO dto = this.mealToMealDTO.convert(meal);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
		
	
	
	@GetMapping(value = "/getColdAppetizerMeals")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getColdAppetizerMeals(){
		List<Meal> meals = mealService.getAllMealbyMealType(MealType.COLD_APPETIZER);
		List<MealDTO> dto = this.mealToMealDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/getHotAppetizerMeals")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getHotAppetizerMeals(){
		List<Meal> meals = mealService.getAllMealbyMealType(MealType.HOT_APPETIZER);
		List<MealDTO> dto = this.mealToMealDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getMainCourseMeals")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getMainCourseMeals(){
		List<Meal> meals = mealService.getAllMealbyMealType(MealType.MAIN_COURSE);
		List<MealDTO> dto = this.mealToMealDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getDesertMeals")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getDesertMeals(){
		List<Meal> meals = mealService.getAllMealbyMealType(MealType.DESERT);
		List<MealDTO> dto = this.mealToMealDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getSaladMeals")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getSaladMeals(){
		List<Meal> meals = mealService.getAllMealbyMealType(MealType.SALAD);
		List<MealDTO> dto = this.mealToMealDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getAppendicesMeals")
	@PreAuthorize("hasRole('ROLE_CHEF')")
	public ResponseEntity<List<MealDTO>> getAppendicesMeals(){
		List<Meal> meals = mealService.getAllMealbyMealType(MealType.APPENDICES);
		List<MealDTO> dto = this.mealToMealDTO.convertList(meals);
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
