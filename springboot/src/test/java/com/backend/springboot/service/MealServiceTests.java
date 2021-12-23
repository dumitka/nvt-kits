package com.backend.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.repository.MealRepository;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.backend.springboot.exception.MealDoesNotExist;
import com.backend.springboot.exception.MealAlreadyExistsException;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.CHANGE_EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.CHANGE_EXISTING_MEAL2;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MealServiceTests {

	@Autowired
	private MealService mealService;
	
	@MockBean
	private MealRepository mealRepository;
	
	
	
	//no need to make test for function getAllMealbyMealType,
	//it return getting list from repository, which is tested in MealRepositoryTests
	
	@Test(expected = MealAlreadyExistsException.class)
	public void addMeal_MealAlreadyExists_Exception() throws Exception {
		Mockito.when(mealRepository.findMealByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION)).thenReturn(Optional.of(EXISTING_MEAL));
		mealService.addMeal(EXISTING_MEAL);
		verify(mealRepository, times(1)).findMealByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
	}
	
	
	@Test
	public void addMeal_MealDoesNotExist_AddedMeal() throws Exception {
		Mockito.when(mealRepository.findMealByNameAndDescription(NON_EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION)).thenReturn(Optional.empty());
		Mockito.when(mealRepository.save(NON_EXISTING_MEAL)).thenReturn(NON_EXISTING_MEAL);
		boolean response = mealService.addMeal(NON_EXISTING_MEAL);
		assertTrue(response);
		verify(mealRepository, times(1)).findMealByNameAndDescription(NON_EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
		verify(mealRepository, times(1)).save(NON_EXISTING_MEAL);
	}
	
	

	@Test(expected = MealDoesNotExist.class)
	public void changeMeal_MealDoesNotExist_Exception() throws Exception {
		Mockito.when(mealRepository.findById(NON_EXISTING_MEAL_ID)).thenReturn(Optional.empty());
		mealService.changeMeal(CHANGE_EXISTING_MEAL2);
		verify(mealRepository, times(1)).findById(NON_EXISTING_MEAL_ID);
	}
	
	

	@Test
	public void changeMeal_MealExists_changedMeal() throws Exception {
		Mockito.when(mealRepository.findById(EXISTING_MEAL_ID)).thenReturn(Optional.of(EXISTING_MEAL));
		Mockito.when(mealRepository.save(EXISTING_MEAL)).thenReturn(EXISTING_MEAL);
		
		Boolean response = mealService.changeMeal(CHANGE_EXISTING_MEAL);
		
		verify(mealRepository, times(1)).findById(EXISTING_MEAL_ID);
		verify(mealRepository, times(1)).save(EXISTING_MEAL);
		assertTrue(response);
	}
	
	
	
	
	

	@Test(expected = MealDoesNotExist.class)
	public void deleteMeal_MealDoesNotExist_Exception() throws Exception {
		Mockito.when(mealRepository.findById(NON_EXISTING_MEAL_ID)).thenReturn(Optional.empty());
		mealService.delete(NON_EXISTING_MEAL);
		verify(mealRepository, times(1)).findById(NON_EXISTING_MEAL_ID);
	}
	
	

	@Test
	public void delete_MealExists_deletedMeal() throws Exception {
		Mockito.when(mealRepository.findMealById(EXISTING_MEAL_ID)).thenReturn(Optional.of(EXISTING_MEAL));
		Mockito.when(mealRepository.save(EXISTING_MEAL)).thenReturn(EXISTING_MEAL);
		Boolean response = mealService.delete(EXISTING_MEAL);
		verify(mealRepository, times(1)).findMealById(EXISTING_MEAL_ID);
		verify(mealRepository, times(1)).save(EXISTING_MEAL);
		assertTrue(response);
	}
	
}
