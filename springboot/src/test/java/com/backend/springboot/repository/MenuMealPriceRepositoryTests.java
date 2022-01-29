package com.backend.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.MenuMealPrice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.backend.springboot.constants.MenuMealPriceConstants.CURRENT_MENU_ID;
import static com.backend.springboot.constants.MenuMealPriceConstants.SET_MEAL_PRICES_OF_CURRENT_MENU_SIZE;
import static com.backend.springboot.constants.MenuMealPriceConstants.LIST_MEAL_PRICES_OF_CURRENT_MENU_SIZE;
import static com.backend.springboot.constants.MenuMealPriceConstants.NOT_CURRENT_MENU_ID;
import static com.backend.springboot.constants.MenuMealPriceConstants.SET_MEAL_PRICES_OF_NOT_CURRENT_MENU_SIZE;
import static com.backend.springboot.constants.MenuMealPriceConstants.EXISTING_BOND_MENU_ID;
import static com.backend.springboot.constants.MenuMealPriceConstants.EXISTING_BOND_MEAL_PRICE_ID;
import static com.backend.springboot.constants.MenuMealPriceConstants.NOT_EXISTING_BOND_MENU_ID;
import static com.backend.springboot.constants.MenuMealPriceConstants.NOT_EXISTING_BOND_MEAL_PRICE_ID;
import static com.backend.springboot.constants.MenuMealPriceConstants.LIST_MEAL_PRICES_OF_NOT_CURRENT_MENU_SIZE;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MenuMealPriceRepositoryTests {
	
	@Autowired
	MenuMealPriceRepository menuMealPriceRepository;
	
	
	
	@Test
	public void findAllMealsPricesByMenuId_CurrentMenuId_ListOfMealPricesOfCurrentMenu() {
		List<MealPrice> found = menuMealPriceRepository.findAllMealsPricesByMenuId(CURRENT_MENU_ID);
		assertEquals(LIST_MEAL_PRICES_OF_CURRENT_MENU_SIZE, found.size());
	}
	
	
	@Test
	public void findAllMealsPricesByMenuId_NotCurrentMenuId_ListOfMealPricesOfCurrentMenu() {
		List<MealPrice> found = menuMealPriceRepository.findAllMealsPricesByMenuId(NOT_CURRENT_MENU_ID);
		assertEquals(LIST_MEAL_PRICES_OF_NOT_CURRENT_MENU_SIZE, found.size());
	}
	
	
	@Test
	public void findAllMenuMealPricesByMenuId_ExistingBond_MenuMealPrices(){
		Optional<MenuMealPrice> found = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(EXISTING_BOND_MEAL_PRICE_ID, EXISTING_BOND_MENU_ID);
		assertTrue(found.isPresent());
		assertEquals(EXISTING_BOND_MEAL_PRICE_ID, found.get().getMealPrice().getId());
		assertEquals(EXISTING_BOND_MENU_ID, found.get().getMenu().getId());
	}
	
	
	@Test
	public void findAllMenuMealPricesByMenuId_NotExistingBond_Null(){
		Optional<MenuMealPrice> found = menuMealPriceRepository.findMenuMealPriceByMealPriceIdAndMenuId(NOT_EXISTING_BOND_MEAL_PRICE_ID, NOT_EXISTING_BOND_MENU_ID);
		assertFalse(found.isPresent());
	}
	
	
}
