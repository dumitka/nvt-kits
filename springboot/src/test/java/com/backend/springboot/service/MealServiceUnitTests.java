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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;


import com.backend.springboot.model.Meal;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_ID;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_NAME;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL_DESCRIPTION;
import static com.backend.springboot.constants.MealConstants.MEALTYPE_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.LIST_OF_MAIN_COURSE;
import static com.backend.springboot.constants.MealConstants.LIST_OF_MAIN_COURSE_SIZE;
import static com.backend.springboot.constants.MealConstants.NEW_MEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.CHANGED_MEAL;
import static com.backend.springboot.constants.MealConstants.DELETED_MEAL;
import static com.backend.springboot.constants.MealConstants.DELETED_MEAL_ID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MealServiceUnitTests {

	@Autowired
	private MealService mealService;
	
	@MockBean
	private MealRepository mealRepository;
	
	
	//S1
	@Test
	public void exists_MealExists_True() throws Exception {
		Mockito.when(mealRepository.findById(EXISTING_MEAL_ID)).thenReturn(Optional.of(EXISTING_MEAL));
		boolean returnValue = mealService.exists(EXISTING_MEAL_ID);
		assertTrue(returnValue);
		verify(mealRepository, times(1)).findById(EXISTING_MEAL_ID);
	}
	
	
	//S2
	@Test
	public void exists_MealDoesNotExist_False() throws Exception {
		Mockito.when(mealRepository.findById(NON_EXISTING_MEAL_ID)).thenReturn(Optional.empty());
		boolean returnValue = mealService.exists(NON_EXISTING_MEAL_ID);
		assertFalse(returnValue);
		verify(mealRepository, times(1)).findById(NON_EXISTING_MEAL_ID);
	}
	
	
	//S3
	@Test
	public void findByNameAndDescription_ExistingNameAndDescription_True() {
		Mockito.when(mealRepository.findMealByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION)).thenReturn(Optional.of(EXISTING_MEAL));
		boolean returnValue = mealService.findByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
		assertTrue(returnValue);
		verify(mealRepository, times(1)).findMealByNameAndDescription(EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
	}
	
	
	//S4
	@Test
	public void findByNameAndDescription_NonExistingNameAndExistingDescription_False() {
		Mockito.when(mealRepository.findMealByNameAndDescription(NON_EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION)).thenReturn(Optional.empty());
		boolean returnValue = mealService.findByNameAndDescription(NON_EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
		assertFalse(returnValue);
		verify(mealRepository, times(1)).findMealByNameAndDescription(NON_EXISTING_MEAL_NAME, EXISTING_MEAL_DESCRIPTION);
	}
	
	
	//S5
	@Test
	public void findByNameAndDescription_ExistingNameAndNonExistingDescription_False() {
		Mockito.when(mealRepository.findMealByNameAndDescription(EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION)).thenReturn(Optional.empty());
		boolean returnValue = mealService.findByNameAndDescription(EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
		assertFalse(returnValue);
		verify(mealRepository, times(1)).findMealByNameAndDescription(EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
	}
	
	
	
	//S6
	@Test
	public void findByNameAndDescription_NonExistingNameAndNonExistingDescription_False() {
		Mockito.when(mealRepository.findMealByNameAndDescription(NON_EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION)).thenReturn(Optional.empty());
		boolean returnValue = mealService.findByNameAndDescription(NON_EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
		assertFalse(returnValue);
		verify(mealRepository, times(1)).findMealByNameAndDescription(NON_EXISTING_MEAL_NAME, NON_EXISTING_MEAL_DESCRIPTION);
	}
	
		
	//S7
	@Test
	public void getAllMealbyMealType_MealTypeMainCourse_ListofMeals() {
		Mockito.when(mealRepository.findMealbyMealType(MEALTYPE_MAIN_COURSE)).thenReturn(LIST_OF_MAIN_COURSE);
		List<Meal> returnList = mealService.getAllMealbyMealType(MEALTYPE_MAIN_COURSE);
		assertFalse(returnList.isEmpty());
		assertEquals(LIST_OF_MAIN_COURSE_SIZE, returnList.size());
		assertEquals(LIST_OF_MAIN_COURSE.get(0).getId(), returnList.get(0).getId());
		assertEquals(LIST_OF_MAIN_COURSE.get(1).getId(), returnList.get(1).getId());
		verify(mealRepository, times(1)).findMealbyMealType(MEALTYPE_MAIN_COURSE);
	}
	
	
	//S8
	@Test
	public void addMeal_forwardMeal_True() {
		Mockito.when(mealRepository.save(NEW_MEAL)).thenReturn(NEW_MEAL);
		boolean returnValue = mealService.addMeal(NEW_MEAL);
		assertTrue(returnValue);
		verify(mealRepository, times(1)).save(NEW_MEAL);
	}
	
	
	
	//S9
	@Test
	public void changeMeal_NonExistingMeal_False() {
		Mockito.when(mealRepository.findById(NON_EXISTING_MEAL_ID)).thenReturn(Optional.empty());
		boolean returnValue = mealService.changeMeal(NON_EXISTING_MEAL);
		assertFalse(returnValue);
		verify(mealRepository, times(1)).findById(NON_EXISTING_MEAL_ID);
	}
		
	
	
	//S10
	@Test
	public void changeMeal_ExistingMeal_True() {
		Mockito.when(mealRepository.findById(EXISTING_MEAL_ID)).thenReturn(Optional.of(EXISTING_MEAL));
		Mockito.when(mealRepository.save(EXISTING_MEAL)).thenReturn(EXISTING_MEAL);
		boolean returnValue = mealService.changeMeal(CHANGED_MEAL);
		assertTrue(returnValue);
		verify(mealRepository, times(1)).findById(EXISTING_MEAL_ID);
		verify(mealRepository, times(1)).save(EXISTING_MEAL);
	}
	
	
	
	//S11
	@Test
	public void delete_MealAlreadyDeleted_False() {
		Mockito.when(mealRepository.findById(DELETED_MEAL_ID)).thenReturn(Optional.of(DELETED_MEAL));
		boolean returnValue = mealService.delete(DELETED_MEAL_ID);
		assertFalse(returnValue);
		verify(mealRepository, times(1)).findById(DELETED_MEAL_ID);
	}
	
	
	
	//S12
	@Test
	public void delete_MealIsNotDeleted_True() {
		Mockito.when(mealRepository.findById(EXISTING_MEAL_ID)).thenReturn(Optional.of(EXISTING_MEAL));
		Mockito.when(mealRepository.save(EXISTING_MEAL)).thenReturn(EXISTING_MEAL);
		boolean returnValue = mealService.delete(EXISTING_MEAL_ID);
		assertTrue(returnValue);
		verify(mealRepository, times(1)).findById(EXISTING_MEAL_ID);
		verify(mealRepository, times(1)).save(EXISTING_MEAL);
	}
	
}
