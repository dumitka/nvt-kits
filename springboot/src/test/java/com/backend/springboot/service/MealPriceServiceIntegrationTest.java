package com.backend.springboot.service;

import static com.backend.springboot.constants.MealPriceConstants.CHANGED_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.LIST_ELEMENT1;
import static com.backend.springboot.constants.MealPriceConstants.LIST_ELEMENT3;
import static com.backend.springboot.constants.MealPriceConstants.MENU_ID;
import static com.backend.springboot.constants.MealPriceConstants.NEW_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.NON_EXISTING_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.IEXISTING_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.INEW_MEALPRICE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.exception.CurrentMenuNotFoundException;
import com.backend.springboot.exception.MealPriceAlreadyExistsException;
import com.backend.springboot.exception.MealPriceNotFoundException;
import com.backend.springboot.model.MealPrice;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealPriceServiceIntegrationTest {
	@Autowired 
	private MealPriceService mealPriceService;
	
	
	
	
	
	@Test(expected = MealPriceAlreadyExistsException.class)
	public void addMealPrice_MealAlreadyExistsInMenu_Exception() throws Exception {
		mealPriceService.addMealPrice(IEXISTING_MEALPRICE);
	}
	
	
	
	@Test
	public void addMealPrice_EverythingOK_addedMeal() throws Exception {
		boolean response = mealPriceService.addMealPrice(INEW_MEALPRICE);
		assertTrue(response);
	}
	
	
	/*
	@Test(expected = CurrentMenuNotFoundException.class)
	public void changeMealPrice_CurrentMenuNotFound_Exception() throws Exception {
		mealPriceService.changeMealPrice(CHANGED_MEALPRICE);
	}
	
	
	
	@Test(expected = MealPriceNotFoundException.class)
	public void changeMealPrice_MealPriceNotFound_Exception() throws Exception {
		mealPriceService.changeMealPrice(NON_EXISTING_MEALPRICE);
	}
	
	
	
	@Test
	public void changeMealPrice_EverythingOK_changedMealPrice() throws Exception {
		boolean response = mealPriceService.changeMealPrice(CHANGED_MEALPRICE);
		assertTrue(response);
	}
	
	
	
	@Test(expected = CurrentMenuNotFoundException.class)
	public void deleteMealPriceFromMenu_CurrentMenuNotFound_Exception() throws Exception {
		mealPriceService.deleteMealPriceFromMenu(EXISTING_MEALPRICE);
	}
	
	
	
	@Test(expected = MealPriceNotFoundException.class)
	public void deleteMealPriceFromMenu_MealPriceNotFound_Exception() throws Exception {
		mealPriceService.deleteMealPriceFromMenu(NON_EXISTING_MEALPRICE);
	}
	
	
	
	
	@Test
	public void deleteMealPriceFromMenu_EverythingOK_deletedMealPrice() throws Exception {
		boolean response = mealPriceService.deleteMealPriceFromMenu(EXISTING_MEALPRICE);
		assertTrue(response);
	}
	
	
	
	@Test
	public void getMealPricesThatAreNotInMenu_gettingMealsFromCUrrentMenu_List() {
		List<MealPrice> returnList = mealPriceService.getMealPricesThatAreNotInMenu(MENU_ID);
		assertEquals(2, returnList.size());
		assertFalse(returnList.contains(LIST_ELEMENT1));
		assertFalse(returnList.contains(LIST_ELEMENT3));
	}
	*/
}
