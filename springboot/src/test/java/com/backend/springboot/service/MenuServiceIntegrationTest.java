package com.backend.springboot.service;

import static com.backend.springboot.constants.MenuConstants.ID_OF_CURRENT_MENU;
import static org.junit.Assert.assertEquals;

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
	

	/*
	@Test(expected = CurrentMenuNotFoundException.class)
	public void getCurrentMenu_CurrentMenuNotFound_Exception() throws Exception {
		menuService.getCurrentMenu();
	}
	*/
	
	
	@Test
	public void getCurrentMenu_CurrentMenuFound_Menu() throws Exception {
		Menu returnValue = menuService.getCurrentMenu();
		assertEquals(ID_OF_CURRENT_MENU, returnValue.getId());
	}
}
