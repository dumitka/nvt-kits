package com.backend.springboot.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import com.backend.springboot.exception.MealAlreadyExistsException;
import com.backend.springboot.exception.MealDoesNotExist;


import static com.backend.springboot.constants.MealConstants.CHANGE_EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.CHANGE_EXISTING_MEAL2;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.IMEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL;

import static org.junit.Assert.assertTrue;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealServiceIntegrationTest {
	
	@Autowired
	private MealService mealService;
	
	@Test(expected = MealAlreadyExistsException.class)
	public void addMeal_MealAlreadyExists_Exception() throws Exception {
		mealService.addMeal(IMEAL);
	}
	
	
	@Test
	public void addMeal_MealDoesNotExist_AddedMeal() throws Exception {
		boolean response = mealService.addMeal(NON_EXISTING_MEAL);
		assertTrue(response);
	}
	
	
	@Test(expected = MealDoesNotExist.class)
	public void changeMeal_MealDoesNotExist_Exception() throws Exception {
		mealService.changeMeal(CHANGE_EXISTING_MEAL2);
	}
	
	
	
	@Test
	public void changeMeal_MealExists_changedMeal() throws Exception {
		Boolean response = mealService.changeMeal(CHANGE_EXISTING_MEAL);
		assertTrue(response);
	}
	
	
	
	@Test(expected = MealDoesNotExist.class)
	public void deleteMeal_MealDoesNotExist_Exception() throws Exception {
		mealService.delete(NON_EXISTING_MEAL);
	}
	
	

	@Test
	public void delete_MealExists_deletedMeal() throws Exception {
		Boolean response = mealService.delete(EXISTING_MEAL);
		assertTrue(response);
	}
	
	
}
