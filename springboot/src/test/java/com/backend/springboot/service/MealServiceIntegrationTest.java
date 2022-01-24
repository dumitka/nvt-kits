package com.backend.springboot.service;


import static com.backend.springboot.constants.MealConstants.CHANGED_MEAL;
import static com.backend.springboot.constants.MealConstants.DELETED_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.LIST_OF_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.LIST_OF_MAIN_COURSE_SIZE;
import static com.backend.springboot.constants.MealConstants.MEALTYPE_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.NEW_MEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.backend.springboot.repository.MealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.model.Meal;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealServiceIntegrationTest {
	
	@Autowired
	private MealService mealService;

	@Autowired
	private MealRepository mealRepository;
	
	//S1
	@Test
	public void exists_MealExists_True() throws Exception {
		boolean returnValue = mealService.exists(EXISTING_MEAL_ID);
		assertTrue(returnValue);
	}
	
	
	//S2
	@Test
	public void exists_MealDoesNotExist_False() throws Exception {
		boolean returnValue = mealService.exists(NON_EXISTING_MEAL_ID);
		assertFalse(returnValue);
	}
	
	
	//S3
	@Test
	public void findByNameAndDescription_ExistingNameAndDescription_True() {
		boolean returnValue = mealService.findByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
		assertTrue(returnValue);
	}
	
	
	//S4
	@Test
	public void findByNameAndDescription_NonExistingNameAndExistingDescription_False() {
		boolean returnValue = mealService.findByNameAndDescription(NON_EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
		assertFalse(returnValue);
	}
	
	
	//S5
	@Test
	public void findByNameAndDescription_ExistingNameAndNonExistingDescription_False() {
		boolean returnValue = mealService.findByNameAndDescription(EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
		assertFalse(returnValue);
	}
	
	
	
	//S6
	@Test
	public void findByNameAndDescription_NonExistingNameAndNonExistingDescription_False() {
		boolean returnValue = mealService.findByNameAndDescription(NON_EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
		assertFalse(returnValue);
	}
	
		
	//S7
	@Test
	public void getAllMealbyMealType_MealTypeMainCourse_ListofMeals() {
		List<Meal> returnList = mealService.getAllMealbyMealType(MEALTYPE_MAIN_COURSE);
		assertFalse(returnList.isEmpty());
		assertEquals(LIST_OF_MAIN_COURSE_SIZE, returnList.size());
		assertEquals(LIST_OF_MAIN_COURSE.get(0).getId(), returnList.get(0).getId());
		assertEquals(LIST_OF_MAIN_COURSE.get(1).getId(), returnList.get(1).getId());
	}
	
	
	//S8
	@Test
	public void addMeal_forwardMeal_True() {
		boolean returnValue = mealService.addMeal(NEW_MEAL);
		assertTrue(returnValue);
	}
	
	
	
	//S9
	@Test
	public void changeMeal_NonExistingMeal_False() {
		boolean returnValue = mealService.changeMeal(NON_EXISTING_MEAL);
		assertFalse(returnValue);
	}
		
	
	
	//S10
	@Test
	public void changeMeal_ExistingMeal_True() {
		boolean returnValue = mealService.changeMeal(CHANGED_MEAL);
		assertTrue(returnValue);
	}
	
	
	
	//S11
	@Test
	public void delete_MealAlreadyDeleted_False() {
		boolean returnValue = mealService.delete(DELETED_MEAL_ID);
		assertFalse(returnValue);
	}
	
	
	
	//S12
	@Test
	public void delete_MealIsNotDeleted_True() {
		boolean returnValue = mealService.delete(EXISTING_MEAL_ID);
		assertTrue(returnValue);
		Meal meal = this.mealRepository.findById(EXISTING_MEAL_ID).orElse(null);
		meal.setDeleted(false);
		this.mealRepository.save(meal);
	}
}
