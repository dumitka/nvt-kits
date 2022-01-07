package com.backend.springboot.service;



import static com.backend.springboot.constants.MealConstants.MEALTYPE_MAIN_COURSE;
import static com.backend.springboot.constants.MealPriceConstants.CHANGED_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU;
import static com.backend.springboot.constants.MealPriceConstants.MAIN_COURSE_MEAL_PRICE_LIST;
import static com.backend.springboot.constants.MealPriceConstants.MAIN_COURSE_MEAL_PRICE_LIST_SIZE;
import static com.backend.springboot.constants.MealPriceConstants.NEW_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.NON_EXISTING_MEAL_PRICE;
import static com.backend.springboot.constants.MenuConstants.CURRENT_MENU;
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
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealPriceServiceIntegrationTest {
	
	@Autowired 
	private MealPriceService mealPriceService;
	
	
	
	@Test
	public void exists_MealPriceExists_True() {
		boolean returnValue = mealPriceService.exists(EXISTING_MEAL_PRICE.getId());
		assertTrue(returnValue);
	}
	
	

	@Test
	public void exists_MealPriceDoesNotExist_False() {
		boolean returnValue = mealPriceService.exists(NON_EXISTING_MEAL_PRICE.getId());
		assertFalse(returnValue);
	}
	
	
	
	@Test
	public void getAllMealPricebyMealType_MainCourse_List() {
		List<MealPrice> returnList = mealPriceService.getAllMealPricebyMealType(MEALTYPE_MAIN_COURSE);
		assertEquals(MAIN_COURSE_MEAL_PRICE_LIST_SIZE, returnList.size());
		assertEquals(MAIN_COURSE_MEAL_PRICE_LIST.get(0).getId(), returnList.get(0).getId());
	}
	
	
	
	@Test
	public void addMealPrice_ExistingMenuMealPrice_True() {
		boolean returnValue = mealPriceService.addMealPrice(NEW_MEAL_PRICE);
		assertTrue(returnValue);
	}
	
	

	@Test
	public void changeMealPrice_ExistingMealPrice_True() {
		boolean returnValue = mealPriceService.changeMealPrice(CHANGED_MEAL_PRICE);
		assertTrue(returnValue);
	}
	
	
	@Test
	public void deleteMealPriceFromMenu_ExistingMealPrice_True() {
		boolean returnValue = mealPriceService.deleteMealPriceFromMenu(EXISTING_MEAL_PRICE.getId());
		assertTrue(returnValue);
	}
	
	
	
	@Test
	public void getMealPricesThatAreNotInMenu_EverythingOK_List() {
		List<Meal> returnList = mealPriceService.getMealPricesThatAreNotInMenu(CURRENT_MENU.getId());
		assertEquals(LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU.size(), returnList.size());
		assertEquals(LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU.get(0).getName(), returnList.get(0).getName());
	}
	
	
}
