package com.backend.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.model.Meal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import static com.backend.springboot.constants.MealConstants.MEALTYPE_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.MAIN_COURSE_LIST_IN_DATABASE;
import static com.backend.springboot.constants.MealConstants.NAME_OF_FIRST_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.MEALTYPE_SALAD;
import static com.backend.springboot.constants.MealConstants.SALAD_LIST_IN_DATABASE;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_DESCRIPTION;



@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MealRepositoryTests {
	
	@Autowired
	MealRepository mealRepository;
	
	
	
	@Test 
	public void findMealbyMealType_ExistingMealTypeMeals_List() {
		List<Meal> mealList = mealRepository.findMealbyMealType(MEALTYPE_MAIN_COURSE);
		assertEquals(MAIN_COURSE_LIST_IN_DATABASE, mealList.size()); //expected 2
		assertEquals(MEALTYPE_MAIN_COURSE, mealList.get(0).getType()); //expected main course
		assertEquals(NAME_OF_FIRST_MAIN_COURSE, mealList.get(0).getName());
	}
	
	
	
	@Test 
	public void findMealbyMealType_NonExistingMealTypeMeals_EmptyList() {
		List<Meal> mealList = mealRepository.findMealbyMealType(MEALTYPE_SALAD);
		assertEquals(SALAD_LIST_IN_DATABASE, mealList.size()); //expected 0
	}
	
	
	
	@Test 
	public void findMealById_MealExists_True() {
		Optional<Meal> found = mealRepository.findMealById(EXISTING_MEAL_ID);
		assertTrue(found.isPresent());
		assertEquals(EXISTING_MEAL_ID, found.get().getId()); //1
		assertEquals(EXISTING_MEAL_NAME, found.get().getName()); //kajgana
	}
	
	
	@Test 
	public void findMealById_MealNotExists_True() {
		Optional<Meal> found = mealRepository.findMealById(NON_EXISTING_MEAL_ID);
		assertFalse(found.isPresent());
	}
	
	
	@Test 
	public void findMealByNameAndDescription_CorrectNameAndDescription_Meal() {
		Optional<Meal> found = mealRepository.findMealByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION); 
		assertTrue(found.isPresent());
		assertEquals(EXISTING_MEAL_ID, found.get().getId()); //1
		assertEquals(EXISTING_MEAL_NAME, found.get().getName()); //kajgana
		assertEquals(EXISTING_MEAL_DESCRIPTION, found.get().getDescription()); //kajgana
	}
	
}
