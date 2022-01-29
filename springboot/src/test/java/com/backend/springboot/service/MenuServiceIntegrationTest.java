package com.backend.springboot.service;

import static com.backend.springboot.constants.MenuConstants.ID_OF_CURRENT_MENU;
import static com.backend.springboot.constants.MenuConstants.MEAL_PRICES_DTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.model.Menu;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MenuServiceIntegrationTest {
	@Autowired
	private MenuService menuService;
	

	/* this test will run true if you put all menus on current false
	@Test
	public void getCurrentMenu_CurrentMenuNotFound_Null(){
		menuService.getCurrentMenu();
	}
	*/
	
	
	@Test
	public void getCurrentMenu_CurrentMenuFound_Menu() throws Exception {
		Menu returnValue = menuService.getCurrentMenu();
		assertEquals(ID_OF_CURRENT_MENU, returnValue.getId());
	}
	
	
	@Test 
	public void addNewMenu_MealPriceGiven_True() {
		boolean returnValue = menuService.addNewMenu(MEAL_PRICES_DTO);
		assertTrue(returnValue);
	}
}
