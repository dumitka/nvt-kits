package com.backend.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.repository.MealPriceRepository;
import com.backend.springboot.repository.MealRepository;
import com.backend.springboot.repository.MenuMealPriceRepository;
import com.backend.springboot.repository.MenuRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;


import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.NON_EXISTING_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.MAIN_COURSE_MEAL_PRICE_LIST;
import static com.backend.springboot.constants.MealPriceConstants.MAIN_COURSE_MEAL_PRICE_LIST_SIZE;
import static com.backend.springboot.constants.MealConstants.MEALTYPE_MAIN_COURSE;
//import static com.backend.springboot.constants.MealPriceConstants.NEW_MEAL_PRICE;
import static com.backend.springboot.constants.MenuConstants.CURRENT_MENU;
/*
import static com.backend.springboot.constants.MenuConstants.NEW_MENU;
import static com.backend.springboot.constants.MenuConstants.MEAL_PRICES_FOR_CURRENT_MENU;
import static com.backend.springboot.constants.MealPriceConstants.NEW_MENU_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.CHANGED_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MENU_MEAL_PRICE;
*/
import static com.backend.springboot.constants.MealPriceConstants.LIST_OF_ALL_MEALS;
import static com.backend.springboot.constants.MealPriceConstants.LIST_OF_MEAL_PRICES_IN_CURRENT_MENU;
import static com.backend.springboot.constants.MealPriceConstants.LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU;
import static com.backend.springboot.constants.MealPriceConstants.CURRENT_MENU_MEAL_PRICE;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealPriceServiceUnitTests {
	@Autowired 
	private MealPriceService mealPriceService;
	
	@MockBean
	private MealPriceRepository mealPriceRepository;
	
	@MockBean
	private MenuRepository menuRepository;
	
	@MockBean
	private MenuMealPriceRepository menuMealPriceRepository;
	
	@MockBean
	private MealRepository mealRepository;
	
	//TEST 1
	@Test
	public void exists_MealPriceExists_True() {
		Mockito.when(mealPriceRepository.findById(EXISTING_MEAL_PRICE.getId())).thenReturn(Optional.of(EXISTING_MEAL_PRICE));
		boolean returnValue = mealPriceService.exists(EXISTING_MEAL_PRICE.getId());
		assertTrue(returnValue);
		verify(mealPriceRepository, times(1)).findById(EXISTING_MEAL_PRICE.getId());
	}
	
	
	//TEST 2
	@Test
	public void exists_MealPriceDoesNotExist_False() {
		Mockito.when(mealPriceRepository.findById(NON_EXISTING_MEAL_PRICE.getId())).thenReturn(Optional.empty());
		boolean returnValue = mealPriceService.exists(NON_EXISTING_MEAL_PRICE.getId());
		assertFalse(returnValue);
		verify(mealPriceRepository, times(1)).findById(NON_EXISTING_MEAL_PRICE.getId());
	}
	
	
	//TEST 3
	@Test
	public void getAllMealPricebyMealType_MainCourse_List() {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU_MEAL_PRICE));
		Mockito.when(menuMealPriceRepository.findAllMealsPricesByMenuIdAndMealType(CURRENT_MENU_MEAL_PRICE.getId(), MEALTYPE_MAIN_COURSE)).thenReturn(MAIN_COURSE_MEAL_PRICE_LIST);
		
		List<MealPrice> returnList = mealPriceService.getAllMealPricebyMealType(MEALTYPE_MAIN_COURSE);
		assertEquals(MAIN_COURSE_MEAL_PRICE_LIST_SIZE, returnList.size());
		assertEquals(MAIN_COURSE_MEAL_PRICE_LIST.get(0).getId(), returnList.get(0).getId());
		verify(menuRepository, times(1)).findByCurrent();
		verify(menuMealPriceRepository, times(1)).findAllMealsPricesByMenuIdAndMealType(CURRENT_MENU_MEAL_PRICE.getId(), MEALTYPE_MAIN_COURSE);
	}
	
	
	/*
	@Test
	public void addMealPrice_ExistingMenuMealPrice_True() {
		Mockito.when(mealPriceRepository.save(NEW_MEAL_PRICE)).thenReturn(NEW_MEAL_PRICE);
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));
		Mockito.when(menuRepository.save(CURRENT_MENU)).thenReturn(CURRENT_MENU);
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(NEW_MENU));
		
		boolean returnValue = mealPriceService.addMealPrice(NEW_MEAL_PRICE);
		
		assertTrue(returnValue);
		verify(mealPriceRepository, times(1)).save(NEW_MEAL_PRICE);
		verify(menuRepository, times(2)).findByCurrent();
		verify(menuRepository, times(1)).save(CURRENT_MENU);
		
		
	}
	
	/*

	@Test
	public void changeMealPrice_ExistingMealPrice_True() {
		Mockito.when(mealPriceRepository.findById(CHANGED_MEAL_PRICE.getId())).thenReturn(Optional.of(EXISTING_MEAL_PRICE));
		Mockito.when(mealPriceRepository.save(EXISTING_MEAL_PRICE)).thenReturn(EXISTING_MEAL_PRICE);
		boolean returnValue = mealPriceService.changeMealPrice(CHANGED_MEAL_PRICE);
		assertTrue(returnValue);
		verify(mealPriceRepository, times(1)).findById(CHANGED_MEAL_PRICE.getId());
		verify(mealPriceRepository, times(1)).save(EXISTING_MEAL_PRICE);
	}
	
	
	@Test
	public void deleteMealPriceFromMenu_ExistingMealPrice_True() {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));
		Mockito.when(menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_MEAL_PRICE.getId(), CURRENT_MENU.getId())).thenReturn(Optional.of(EXISTING_MENU_MEAL_PRICE));
		Mockito.when(mealPriceRepository.findById(EXISTING_MEAL_PRICE.getId())).thenReturn(Optional.of(EXISTING_MEAL_PRICE));
		
		Mockito.when(mealPriceRepository.save(EXISTING_MEAL_PRICE)).thenReturn(EXISTING_MEAL_PRICE);
		Mockito.when(menuMealPriceRepository.save(EXISTING_MENU_MEAL_PRICE)).thenReturn(EXISTING_MENU_MEAL_PRICE);
		Mockito.when(menuRepository.save(CURRENT_MENU)).thenReturn(CURRENT_MENU);
		boolean returnValue = mealPriceService.deleteMealPriceFromMenu(EXISTING_MEAL_PRICE.getId());
		assertTrue(returnValue);
		verify(menuRepository, times(1)).findByCurrent();
		verify(menuMealPriceRepository, times(1)).findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_MEAL_PRICE.getId(), CURRENT_MENU.getId());
		verify(mealPriceRepository, times(1)).findById(EXISTING_MEAL_PRICE.getId());
		verify(mealPriceRepository, times(1)).save(EXISTING_MEAL_PRICE);
		verify(menuMealPriceRepository, times(1)).save(EXISTING_MENU_MEAL_PRICE);
		verify(menuRepository, times(1)).save(CURRENT_MENU);
	}
	*/
	
	
	
	@Test
	public void getMealPricesThatAreNotInMenu_EverythingOK_List() {
		Mockito.when(mealRepository.findAll()).thenReturn(LIST_OF_ALL_MEALS);
		Mockito.when(menuMealPriceRepository.findAllMealsPricesByMenuId(CURRENT_MENU.getId())).thenReturn(LIST_OF_MEAL_PRICES_IN_CURRENT_MENU);
		List<Meal> returnList = mealPriceService.getMealPricesThatAreNotInMenu(CURRENT_MENU.getId());
		assertEquals(LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU.size(), returnList.size());
		assertEquals(LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU.get(0).getName(), returnList.get(0).getName());
		verify(mealRepository, times(1)).findAll();
		verify(menuMealPriceRepository, times(1)).findAllMealsPricesByMenuId(CURRENT_MENU.getId());
	}
	
}
