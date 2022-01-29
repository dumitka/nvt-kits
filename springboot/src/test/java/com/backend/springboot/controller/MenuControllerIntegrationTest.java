package com.backend.springboot.controller;

import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MenuConstants.MEAL_PRICES_DTO;
import static com.backend.springboot.constants.MenuConstants.NOT_IN_MENU_MEALS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.backend.springboot.dtoTransformation.MealToMealDTO;
import com.backend.springboot.model.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MenuControllerIntegrationTest {
	private static final String URL_PREFIX = "/menu/";
	 
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 private String accessToken;
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
	 public void addMealToMenu_EverythingOK_OK() {
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
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.nonexistingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void deleteMealInMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.existingMealPriceDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMealInMenu", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void newMenu_EverythingOK_OK() throws Exception { 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(MEAL_PRICES_DTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "newMenu", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void getMealPricesNotInMenu_EverythingOK_OK() throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getMealPricesNotInMenu", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertEquals(NOT_IN_MENU_MEALS, responseEntity.getBody().length);
	     assertEquals(Integer.valueOf(3), responseEntity.getBody()[0].getId());
	     assertEquals(Integer.valueOf(4), responseEntity.getBody()[1].getId());
	 }
	 
}
