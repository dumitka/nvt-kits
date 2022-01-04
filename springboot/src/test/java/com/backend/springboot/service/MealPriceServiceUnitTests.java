package com.backend.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.exception.CurrentMenuNotFoundException;
import com.backend.springboot.exception.MealPriceAlreadyExistsException;
import com.backend.springboot.exception.MealPriceNotFoundException;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.repository.MealPriceRepository;
import com.backend.springboot.repository.MenuMealPriceRepository;
import com.backend.springboot.repository.MenuRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.CURRENT_MENU;
import static com.backend.springboot.constants.MealPriceConstants.MENU_ID;
import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEAL_PRICE_ID;
import static com.backend.springboot.constants.MealPriceConstants.MENU_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.NEW_MEALPRICE_ID;
import static com.backend.springboot.constants.MealPriceConstants.NEW_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.CHANGED_MEALPRICE_ID;
import static com.backend.springboot.constants.MealPriceConstants.CHANGED_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.NON_EXISTING_MEALPRICE;
import static com.backend.springboot.constants.MealPriceConstants.NON_EXISTING_MEAL_PRICE_ID;
import static com.backend.springboot.constants.MealPriceConstants.ALL_MEAL_PRICES;
import static com.backend.springboot.constants.MealPriceConstants.MEALS_FROM_CURRENT_MENU;
import static com.backend.springboot.constants.MealPriceConstants.LIST_ELEMENT1;
import static com.backend.springboot.constants.MealPriceConstants.LIST_ELEMENT3;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MealPriceServiceUnitTests {
	@Autowired 
	private MealPriceService mealPriceService;
	
	@MockBean
	private MealPriceRepository mealPriceRepository;
	
	@MockBean
	private MenuRepository menuRepository;
	
	@MockBean
	private MenuMealPriceRepository menuMealPriceRepository;
	
	
	@Test(expected = CurrentMenuNotFoundException.class)
	public void addMealPrice_CurrentMenuNotFound_Exception() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.empty());
		
		mealPriceService.addMealPrice(EXISTING_MEALPRICE);
		
		verify(menuRepository, times(1)).findByCurrent();
	}
	
	
	
	@Test(expected = MealPriceAlreadyExistsException.class)
	public void addMealPrice_MealAlreadyExistsInMenu_Exception() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));			
		Mockito.when(menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_MEAL_PRICE_ID, MENU_ID)).thenReturn(Optional.of(MENU_MEALPRICE));
		
		mealPriceService.addMealPrice(EXISTING_MEALPRICE);
		
		verify(menuRepository, times(1)).findByCurrent();
		verify(menuMealPriceRepository, times(1)).findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_MEAL_PRICE_ID, MENU_ID);
	}
	
	

	@Test
	public void addMealPrice_EverythingOK_addedMeal() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));
		Mockito.when(menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(NEW_MEALPRICE_ID, MENU_ID)).thenReturn(Optional.empty());
		Mockito.when(menuMealPriceRepository.save(MENU_MEALPRICE)).thenReturn(MENU_MEALPRICE);
		
		boolean response = mealPriceService.addMealPrice(NEW_MEALPRICE);
		
		assertTrue(response);
		
		verify(menuRepository, times(1)).findByCurrent();
		verify(menuMealPriceRepository, times(1)).findMenuMealPriceByMealPriceIdAndMenuId(NEW_MEALPRICE_ID, MENU_ID);
	}
	
	
	
	@Test(expected = CurrentMenuNotFoundException.class)
	public void changeMealPrice_CurrentMenuNotFound_Exception() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.empty());
		
		mealPriceService.changeMealPrice(CHANGED_MEALPRICE);
		
		verify(menuRepository, times(1)).findByCurrent();
	}
	
	
	
	@Test(expected = MealPriceNotFoundException.class)
	public void changeMealPrice_MealPriceNotFound_Exception() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));			
		Mockito.when(mealPriceRepository.findMealPriceById(NON_EXISTING_MEAL_PRICE_ID)).thenReturn(Optional.empty());
		
		mealPriceService.changeMealPrice(NON_EXISTING_MEALPRICE);
		
		verify(menuRepository, times(1)).findByCurrent();
		verify(mealPriceRepository, times(1)).findMealPriceById(NON_EXISTING_MEAL_PRICE_ID);
	}
	
	
	
	@Test
	public void changeMealPrice_EverythingOK_changedMealPrice() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));			
		Mockito.when(mealPriceRepository.findMealPriceById(CHANGED_MEALPRICE_ID)).thenReturn(Optional.of(EXISTING_MEALPRICE));
		Mockito.when(mealPriceRepository.save(EXISTING_MEALPRICE)).thenReturn(EXISTING_MEALPRICE);
		
		boolean response = mealPriceService.changeMealPrice(CHANGED_MEALPRICE);
		
		assertTrue(response);
		
		verify(menuRepository, times(1)).findByCurrent();
		verify(mealPriceRepository, times(1)).findMealPriceById(CHANGED_MEALPRICE_ID);
		verify(mealPriceRepository, times(1)).save(EXISTING_MEALPRICE);
	}
	
	
	
	@Test(expected = CurrentMenuNotFoundException.class)
	public void deleteMealPriceFromMenu_CurrentMenuNotFound_Exception() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.empty());
		
		mealPriceService.deleteMealPriceFromMenu(EXISTING_MEALPRICE);
		
		verify(menuRepository, times(1)).findByCurrent();
	}
	
	
	
	@Test(expected = MealPriceNotFoundException.class)
	public void deleteMealPriceFromMenu_MealPriceNotFound_Exception() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));			
		Mockito.when(mealPriceRepository.findMealPriceById(NON_EXISTING_MEAL_PRICE_ID)).thenReturn(Optional.empty());
		
		mealPriceService.deleteMealPriceFromMenu(NON_EXISTING_MEALPRICE);
		
		verify(menuRepository, times(1)).findByCurrent();
		verify(mealPriceRepository, times(1)).findMealPriceById(NON_EXISTING_MEAL_PRICE_ID);
	}
	
	
	
	
	@Test
	public void deleteMealPriceFromMenu_EverythingOK_deletedMealPrice() throws Exception {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));			
		Mockito.when(menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_MEAL_PRICE_ID, MENU_ID)).thenReturn(Optional.of(MENU_MEALPRICE));
		Mockito.when(menuMealPriceRepository.save(MENU_MEALPRICE)).thenReturn(MENU_MEALPRICE);
		
		boolean response = mealPriceService.deleteMealPriceFromMenu(EXISTING_MEALPRICE);
		
		assertTrue(response);
		
		verify(menuRepository, times(1)).findByCurrent();
		verify(menuMealPriceRepository, times(1)).findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_MEAL_PRICE_ID, MENU_ID);
		verify(menuMealPriceRepository, times(1)).save(MENU_MEALPRICE);
	}
	
	
	
	@Test
	public void getMealPricesThatAreNotInMenu_gettingMealsFromCUrrentMenu_List() {
		Mockito.when(mealPriceRepository.findAll()).thenReturn(ALL_MEAL_PRICES);
		Mockito.when( menuMealPriceRepository.findAllMealsPricesByMenuId(MENU_ID)).thenReturn(MEALS_FROM_CURRENT_MENU);
		
		List<MealPrice> returnList = mealPriceService.getMealPricesThatAreNotInMenu(MENU_ID);
		
		assertEquals(2, returnList.size());
		assertFalse(returnList.contains(LIST_ELEMENT1));
		assertFalse(returnList.contains(LIST_ELEMENT3));
		
		verify(mealPriceRepository, times(1)).findAll();
		verify(menuMealPriceRepository, times(1)).findAllMealsPricesByMenuId(MENU_ID);
	}
	
}
