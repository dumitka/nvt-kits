package com.backend.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.dto.MenuDTO;
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.exception.CurrentMenuNotFoundException;
import com.backend.springboot.exception.MealPriceAlreadyExistsException;
import com.backend.springboot.exception.MealPriceNotFoundException;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MenuService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MenuControllerUnitTests {
		
	private static final String URL_PREFIX = "/menu/";
	 
	 @MockBean
	 private MealPriceService mealPriceService;
	 
	 @MockBean
	 private MenuService service;
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 private String accessToken;
	 
	 private MealPrice newMealPrice;
	 private MealPrice existingMealPrice;
	 private MealPrice nonexistingMealPrice;
	 private Menu menu;
	 
	 
	 
	 
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
	        float STARTING_PRICE = 0;
	        Meal MEAL1 = Meal.builder().id(1).name("Hladno predjelo 1").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 1 opis").mealDifficulty(MealDifficulty.EASY).timePreparation(5).amountNumber(500).amountUnit("g").image("http//www.image1.jpg").build();
	        newMealPrice = MealPrice.builder().id(1).meal(MEAL1).priceAmount(STARTING_PRICE + 200).build();
	        existingMealPrice = MealPrice.builder().id(1).meal(MEAL1).priceAmount(STARTING_PRICE + 200).build();
	        nonexistingMealPrice =  MealPrice.builder().id(1).meal(MEAL1).priceAmount(STARTING_PRICE + 200).build();
	        menu = Menu.builder().id(5).build();
	 }
	 
	 
	 
	 @Test
	 public void getMenu_CurrentMenuNotFoundException_NotFound() throws Exception {
		 given(service.getCurrentMenu()).willThrow(CurrentMenuNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MenuDTO> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMenu", HttpMethod.GET, httpEntity, MenuDTO.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     
	 }
	 
	 
	 
	 
	 @Test
	 public void getMenu_EverythingOK_OK() throws Exception {
		 Integer ID_OF_CURRENT_MENU = 2;
		 float STARTING_PRICE = 0;
		 Meal MEAL1 = Meal.builder().id(1).name("Hladno predjelo 1").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 1 opis").mealDifficulty(MealDifficulty.EASY).timePreparation(5).amountNumber(500).amountUnit("g").image("http//www.image1.jpg").build();
		 Meal MEAL2 = Meal.builder().id(2).name("Hladno predjelo 2").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 2 opis").mealDifficulty(MealDifficulty.MEDIUM).timePreparation(10).amountNumber(300).amountUnit("g").image("http//www.image2.jpg").build();
		 MealPrice ELEMENT1 = MealPrice.builder().id(1).meal(MEAL1).priceAmount(STARTING_PRICE + 200).build();
		 MealPrice ELEMENT2 = MealPrice.builder().id(2).meal(MEAL2).priceAmount(STARTING_PRICE + 300).build();
		 LocalDateTime EXISTING_DATE_MENU = LocalDateTime.of(2020, 10, 1, 0, 0);
		 Menu CURRENT_MENU = Menu.builder().id(ID_OF_CURRENT_MENU).current(true).dateOfValidation(EXISTING_DATE_MENU).build();
			
		 MenuMealPrice MMP1 = MenuMealPrice.builder().id(1).mealPrice(ELEMENT1).deleted(false).menu(CURRENT_MENU).build();
		 MenuMealPrice MMP2 = MenuMealPrice.builder().id(1).mealPrice(ELEMENT2).deleted(false).menu(CURRENT_MENU).build();
		 Set<MenuMealPrice> MEALS_FOR_CURRENT_MENU = Set.of(MMP1, MMP2);
		 CURRENT_MENU.setMenuMealPrices(MEALS_FOR_CURRENT_MENU);
		 
		 given(service.getCurrentMenu()).willReturn(CURRENT_MENU);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MenuDTO> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMenu", HttpMethod.GET, httpEntity, MenuDTO.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 @Test
	 public void addMealToMenu_CurrentMenuNotFoundException_BadRequest() throws Exception {
		 given(mealPriceService.addMealPrice(newMealPrice)).willThrow(CurrentMenuNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	 }
	 
	 
	 @Test
	 public void addMealToMenu_MealPriceAlreadyExistsException_BadRequest() throws Exception {
		 given(mealPriceService.addMealPrice(existingMealPrice)).willThrow(MealPriceAlreadyExistsException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	 }
	 
	 
	 @Test
	 public void addMealToMenu_EverythingOK_OK() throws Exception {
		 given(mealPriceService.addMealPrice(newMealPrice)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_CurrentMenuNotFoundException_BadRequest() throws Exception {
		 given(mealPriceService.changeMealPrice(existingMealPrice)).willThrow(CurrentMenuNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_MealPriceNotFoundException_BadRequest() throws Exception {
		 given(mealPriceService.changeMealPrice(nonexistingMealPrice)).willThrow(MealPriceNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_EverythingOK_OK() throws Exception {
		 given(mealPriceService.changeMealPrice(existingMealPrice)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     //vraca false, kao da nije mokovano
	 }
	 
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_CurrentMenuNotFoundException_BadRequest() throws Exception {
		 given(mealPriceService.deleteMealPriceFromMenu(existingMealPrice)).willThrow(CurrentMenuNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_MealPriceNotFoundException_BadRequest() throws Exception {
		 given(mealPriceService.deleteMealPriceFromMenu(nonexistingMealPrice)).willThrow(MealPriceNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_EverythingOK_OK() throws Exception {
		 given(mealPriceService.deleteMealPriceFromMenu(existingMealPrice)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void newMenu_EverythingOK_OK() throws Exception {
		 given(service.addNewMenu(menu)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(menu, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "newMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 /*
	 @Test
	 public void getMealPricesNotInMenu_CurrentMenuNotFoundException_BadRequest() throws Exception {
		 given(service.getCurrentMenu()).willThrow(CurrentMenuNotFoundException.class);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMealPricesNotInMenu", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }*/
	 
	 
	 
	 
	 
	 @Test
	 public void getMealPricesNotInMenu_EverythingOK_OK() throws Exception {Integer ID_OF_CURRENT_MENU = 2;
		 float STARTING_PRICE = 0;
		 Meal MEAL1 = Meal.builder().id(1).name("Hladno predjelo 1").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 1 opis").mealDifficulty(MealDifficulty.EASY).timePreparation(5).amountNumber(500).amountUnit("g").image("http//www.image1.jpg").build();
		 Meal MEAL2 = Meal.builder().id(2).name("Hladno predjelo 2").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 2 opis").mealDifficulty(MealDifficulty.MEDIUM).timePreparation(10).amountNumber(300).amountUnit("g").image("http//www.image2.jpg").build();
		 MealPrice ELEMENT1 = MealPrice.builder().id(1).meal(MEAL1).priceAmount(STARTING_PRICE + 200).build();
		 MealPrice ELEMENT2 = MealPrice.builder().id(2).meal(MEAL2).priceAmount(STARTING_PRICE + 300).build();
		 List<MealPrice> list = new ArrayList<MealPrice>();
		 list.add(ELEMENT1);
		 list.add(ELEMENT2);
		 
		 LocalDateTime EXISTING_DATE_MENU = LocalDateTime.of(2020, 10, 1, 0, 0);
		 Menu CURRENT_MENU = Menu.builder().id(ID_OF_CURRENT_MENU).current(true).dateOfValidation(EXISTING_DATE_MENU).build();
			
		 MenuMealPrice MMP1 = MenuMealPrice.builder().id(1).mealPrice(ELEMENT1).deleted(false).menu(CURRENT_MENU).build();
		 MenuMealPrice MMP2 = MenuMealPrice.builder().id(1).mealPrice(ELEMENT2).deleted(false).menu(CURRENT_MENU).build();
		 Set<MenuMealPrice> MEALS_FOR_CURRENT_MENU = Set.of(MMP1, MMP2);
		 CURRENT_MENU.setMenuMealPrices(MEALS_FOR_CURRENT_MENU);
		 
		 
		 given(service.getCurrentMenu()).willReturn(CURRENT_MENU);
		 given(mealPriceService.getMealPricesThatAreNotInMenu(CURRENT_MENU.getId())).willReturn(list);
		 
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMealPricesNotInMenu", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
}	
