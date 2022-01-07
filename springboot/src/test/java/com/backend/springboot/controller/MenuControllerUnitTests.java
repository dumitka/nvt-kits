package com.backend.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.MealDTO;
import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.dto.MenuDTO;
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.dtoTransformation.MealPriceToMealWithPriceDTO;
import com.backend.springboot.dtoTransformation.MealToMealDTO;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MenuService;

import static com.backend.springboot.constants.MenuConstants.CURRENT_MENU;
import static com.backend.springboot.constants.MenuConstants.MMP1;
import static com.backend.springboot.constants.MenuConstants.MMP2;
import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MealPriceConstants.LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MenuControllerUnitTests {
		
	private static final String URL_PREFIX = "/menu/";
	 
	 @MockBean
	 private MealPriceService mealPriceService;
	 
	 @MockBean
	 private MenuService menuService;
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 private String accessToken;
	 
	 
	 MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO;
	 
	 
	 //params
	 MealDTO mealDTO;
	 MealWithPriceDTO existingMealPriceDTO;
	 MealWithPriceDTO newMealPriceDTO;
	 MealWithPriceDTO nonexistingMealPriceDTO;
	 MenuDTO newMenuDTO;
	 Menu newMenu;
	 
	 MealToMealDTO mealToMealDTO;
	 
	 
	 
	 @Before
	    public void login() throws Exception {
	        JwtAuthenticationRequest signIn = JwtAuthenticationRequest.builder().username("masa")
	                .password("masa").build();
	        ResponseEntity<UserTokenState> responseEntity =
	                this.restTemplate.postForEntity("/auth/login",
	                		signIn,
	                        UserTokenState.class);
	        this.accessToken = responseEntity.getBody().getAccessToken();
	        
	        
	        //params
	        this.mealToMealDTO = new MealToMealDTO();
	        this.mealDTO = this.mealToMealDTO.convert(EXISTING_MEAL);
	        
	        float startingPrice = 0;
	        this.existingMealPriceDTO = MealWithPriceDTO.builder().id(1).mealDTO(mealDTO).price(startingPrice + 300).build();
	        this.newMealPriceDTO = MealWithPriceDTO.builder().id(-1).mealDTO(mealDTO).price(startingPrice + 100).build();
	        this.nonexistingMealPriceDTO = MealWithPriceDTO.builder().id(-1).mealDTO(mealDTO).price(startingPrice + 100).build();
	        
	        this.newMenuDTO = MenuDTO.builder().current(false).dateOfValidation(LocalDateTime.now()).build();
	        this.newMenu = Menu.builder().current(false).dateOfValidation(LocalDateTime.now()).build();
	 }
	 
	 
	 
	 @Test
	 public void getMenu_CurrentMenuNotFound_NotFound() {
		 given(this.menuService.getCurrentMenu()).willReturn(null);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MenuDTO> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMenu", HttpMethod.GET, httpEntity, MenuDTO.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     assertEquals(Integer.valueOf(0), responseEntity.getBody().getId());
	     
	 }
	 
	 
	 
	 
	 @Test
	 public void getMenu_EverythingOK_OK(){
		 Set<MenuMealPrice> MEALS_FOR_CURRENT_MENU = Set.of(MMP1, MMP2);
		 CURRENT_MENU.setMenuMealPrices(MEALS_FOR_CURRENT_MENU);
		 given(menuService.getCurrentMenu()).willReturn(CURRENT_MENU);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MenuDTO> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMenu", HttpMethod.GET, httpEntity, MenuDTO.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertEquals(CURRENT_MENU.getId(), responseEntity.getBody().getId());
	 }
	 
	 
	 
	 @Test
	 public void addMealToMenu_MealPriceAlreadyExists_BadRequest() {
		 given(mealPriceService.exists(this.existingMealPriceDTO.getId())).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void addMealToMenu_EverythingOK_OK() {
		 given(mealPriceService.exists(this.newMealPriceDTO.getId())).willReturn(false);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_MealPriceNotFoundException_BadRequest(){
		 given(mealPriceService.exists(this.nonexistingMealPriceDTO.getId())).willReturn(false);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_EverythingOK_OK(){
		 given(mealPriceService.exists(this.existingMealPriceDTO.getId())).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_MealPriceNotFound_BadRequest() throws Exception {
		 given(mealPriceService.exists(this.nonexistingMealPriceDTO.getId())).willReturn(false);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_EverythingOK_OK() throws Exception {
		 given(mealPriceService.exists(this.existingMealPriceDTO.getId())).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void newMenu_EverythingOK_OK() throws Exception {
		 given(this.menuService.addNewMenu(this.newMenu)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMenuDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "newMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void getMealPricesNotInMenu_EverythingOK_OK() throws Exception {
		 given(menuService.getCurrentMenu()).willReturn(CURRENT_MENU);
		 given(mealPriceService.getMealPricesThatAreNotInMenu(CURRENT_MENU.getId())).willReturn(LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMealPricesNotInMenu", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertEquals(LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU.size(), responseEntity.getBody().length);
	 }
	 
	 
}	
