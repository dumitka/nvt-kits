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
import com.backend.springboot.dto.MealDTO;
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
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MenuService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MenuControllerIntegrationTest {
	private static final String URL_PREFIX = "/menu/";
	 
	 @Autowired
	 private MealPriceService mealPriceService;
	 
	 @Autowired
	 private MenuService service;
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 private String accessToken;
	 private Meal meal;
	 private Meal newMeal;
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
	        float price = 0;
	        meal = Meal.builder().id(1).name("Kajgana").description("Domace, zdravo").amountNumber(200).amountUnit("g")
		    		   .deleted(false).image("nema").mealDifficulty(MealDifficulty.EASY).timePreparation(5).type(MealType.COLD_APPETIZER)
		    		   .build();
	        newMeal = Meal.builder().id(1).name("Kajgana").description("Domace, zdravo").amountNumber(200).amountUnit("g")
		    		   .deleted(false).image("nema").mealDifficulty(MealDifficulty.EASY).timePreparation(5).type(MealType.COLD_APPETIZER)
		    		   .build();
	        newMealPrice = MealPrice.builder().meal(newMeal).priceAmount(price + 200).deleted(false).build();
	        existingMealPrice = MealPrice.builder().id(1).meal(meal).priceAmount(price + 300).deleted(false).build();
	        nonexistingMealPrice =  MealPrice.builder().id(100).meal(newMeal).priceAmount(price + 500).build();
	        menu = Menu.builder().dateOfValidation(LocalDateTime.now()).restaurant(null).build();
	 }
	 
	 
	 @Test
	 public void getMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MenuDTO> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMenu", HttpMethod.GET, httpEntity, MenuDTO.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void addMealToMenu_MealPriceAlreadyExistsException_BadRequest() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	 }
	 
	 
	 @Test
	 public void addMealToMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMealToMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_MealPriceNotFoundException_BadRequest() throws Exception { 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void changeMealPriceInMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMealPriceInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 

	 
	 
	 
	 @Test
	 public void deleteMealInMenu_MealPriceNotFoundException_BadRequest() throws Exception { 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPrice, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void newMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(menu, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "newMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 @Test
	 public void getMealPricesNotInMenu_EverythingOK_OK() throws Exception {Integer ID_OF_CURRENT_MENU = 2;
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMealPricesNotInMenu", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
}
