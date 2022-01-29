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

import com.backend.springboot.model.Menu;
import com.backend.springboot.repository.MenuRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static com.backend.springboot.constants.MenuConstants.CURRENT_MENU;
import static com.backend.springboot.constants.MenuConstants.ID_OF_CURRENT_MENU;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MenuServiceUnitTests {

	@Autowired
	private MenuService menuService;
	
	@MockBean
	private MenuRepository menuRepository;

	@Test
	public void getCurrentMenu_CurrentMenuNotFound_Null() {
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.empty());
		Menu returnValue = menuService.getCurrentMenu();
		assertEquals(returnValue, null);
		verify(menuRepository, times(1)).findByCurrent();
	}
	
	
	
	@Test
	public void getCurrentMenu_CurrentMenuFound_Menu(){
		Mockito.when(menuRepository.findByCurrent()).thenReturn(Optional.of(CURRENT_MENU));
		Menu returnValue = menuService.getCurrentMenu();
		assertEquals(ID_OF_CURRENT_MENU, returnValue.getId());
		verify(menuRepository, times(1)).findByCurrent();
	}
	
	
	
}
