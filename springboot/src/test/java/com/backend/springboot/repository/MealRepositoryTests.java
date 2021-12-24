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

import static com.backend.springboot.constants.MealConstants.MEALTYPE;
import static com.backend.springboot.constants.MealConstants.LIST_SIZE_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.NAME_OF_FIRST_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_NAME;
import static com.backend.springboot.constants.MealConstants.EXISTING_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.CHECK_ID;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MealRepositoryTests {
	
	@Autowired
	MealRepository mealRepository;
	
	
	
	@Test 
	public void findMealbyMealType_ExistingMealTypeMeals_List() {
		List<Meal> mealList = mealRepository.findMealbyMealType(MEALTYPE);
		assertEquals(LIST_SIZE_MAIN_COURSE, mealList.size());
		assertEquals(MEALTYPE, mealList.get(0).getType());
		assertEquals(NAME_OF_FIRST_MAIN_COURSE, mealList.get(0).getName());
	}
	
	
	@Test 
	public void findMealById_MealExists_True() {
		Optional<Meal> found = mealRepository.findMealById(EXISTING_MEAL_ID);
		assertTrue(found.isPresent());
		assertEquals(EXISTING_MEAL_ID, found.get().getId());
		assertEquals(EXISTING_MEAL_NAME, found.get().getName());
	}
	
	
	@Test 
	public void findMealById_MealNotExists_True() {
		Optional<Meal> found = mealRepository.findMealById(NON_EXISTING_MEAL_ID);
		assertFalse(found.isPresent());
	}
	
	
	@Test 
	public void findMealByNameAndDescription_CorrectNameAndDescription_Meal() {
		Optional<Meal> found = mealRepository.findMealByNameAndDescription(EXISTING_NAME, EXISTING_DESCRIPTION);
		assertTrue(found.isPresent());
		assertEquals(CHECK_ID, found.get().getId());
	}
	
}
