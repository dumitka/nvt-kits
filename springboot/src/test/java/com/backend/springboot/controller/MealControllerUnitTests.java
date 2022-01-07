package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.MealDTO;
import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MealService;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealControllerUnitTests {

	
	 private static final String URL_PREFIX = "/meal/";
	 
	 @MockBean
	 private MealPriceService mealPriceService;
	 
	 @MockBean
	 private MealService mealService;
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 
	 private String accessToken;
	 
	 
	 //params
	 private MealDTO mealThatExistsDTO;
	 private MealDTO mealThatDoenNotExistsDTO;
	 private MealDTO newMealDTO;
	 
	 
	 
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
	     
	       this.mealThatExistsDTO = MealDTO.builder().id(1).name("Kajgana").description("Domace, zdravo").amountNumber(200).amountUnit("g")
	    		   .deleted(false).image("nema").mealDifficulty(MealDifficulty.EASY).timePreparation(5).type(MealType.COLD_APPETIZER)
	    		   .build();
	       this.mealThatDoenNotExistsDTO = MealDTO.builder().id(100).name("Nepostojece jelo").description("Opis nepostojeceg jela").amountNumber(100).amountUnit("g")
	    		   .deleted(false).image("image").mealDifficulty(MealDifficulty.EASY).timePreparation(60).type(MealType.DESERT)
	    		   .build();
	       
	       this.newMealDTO = MealDTO.builder().name("Novo jelo").description("Opis novog jela").amountNumber(100).amountUnit("g")
	    		   .deleted(false).image("image").mealDifficulty(MealDifficulty.EASY).timePreparation(60).type(MealType.DESERT)
	    		   .build();
	 }

	 
	 @Test
	 public void getColdAppetizers_EverythingOK_ListOfMealPrices() {
		 float STARTING_PRICE = 0;
         Meal CA_MEAL1 = Meal.builder().id(1).name("Hladno predjelo 1").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 1 opis").mealDifficulty(MealDifficulty.EASY).timePreparation(5).amountNumber(500).amountUnit("g").image("http//www.image1.jpg").build();
         Meal CA_MEAL2 = Meal.builder().id(2).name("Hladno predjelo 2").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 2 opis").mealDifficulty(MealDifficulty.MEDIUM).timePreparation(10).amountNumber(300).amountUnit("g").image("http//www.image2.jpg").build();
         MealPrice CA_LIST_ELEMENT1 = MealPrice.builder().id(1).meal(CA_MEAL1).priceAmount(STARTING_PRICE + 200).build();
         MealPrice CA_LIST_ELEMENT2 = MealPrice.builder().id(2).meal(CA_MEAL2).priceAmount(STARTING_PRICE + 300).build();
         List<MealPrice> COLD_APPETIZER_LIST = new ArrayList<>();
         COLD_APPETIZER_LIST.add(CA_LIST_ELEMENT1);
         COLD_APPETIZER_LIST.add(CA_LIST_ELEMENT2);
         given(mealPriceService.getAllMealPricebyMealType(MealType.COLD_APPETIZER)).willReturn(COLD_APPETIZER_LIST);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getColdAppetizers", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);
	                
	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 @Test
	 public void getHotAppetizer_EverythingOK_ListOfMealPrices() {
		 float STARTING_PRICE = 0;
         Meal HA_MEAL1 = Meal.builder().id(1).name("Toplo predjelo 1").deleted(false).type(MealType.HOT_APPETIZER).description("Toplo predjelo 1 opis").mealDifficulty(MealDifficulty.EASY).timePreparation(5).amountNumber(500).amountUnit("g").image("http//www.image1.jpg").build();
         Meal HA_MEAL2 = Meal.builder().id(2).name("Toplo predjelo 2").deleted(false).type(MealType.HOT_APPETIZER).description("Toplo predjelo 2 opis").mealDifficulty(MealDifficulty.MEDIUM).timePreparation(10).amountNumber(300).amountUnit("g").image("http//www.image2.jpg").build();
         MealPrice HA_LIST_ELEMENT1 = MealPrice.builder().id(1).meal(HA_MEAL1).priceAmount(STARTING_PRICE + 200).build();
         MealPrice HA_LIST_ELEMENT2 = MealPrice.builder().id(2).meal(HA_MEAL2).priceAmount(STARTING_PRICE + 300).build();
         List<MealPrice> HOT_APPETIZER_LIST = new ArrayList<>();
         HOT_APPETIZER_LIST.add(HA_LIST_ELEMENT1);
         HOT_APPETIZER_LIST.add(HA_LIST_ELEMENT2);
         given(mealPriceService.getAllMealPricebyMealType(MealType.HOT_APPETIZER)).willReturn(HOT_APPETIZER_LIST);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getHotAppetizer", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);
	                
	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	
	 
	 
	 @Test
	 public void addMeal_MealAlreadyExists_BadRequest() {
		 given(this.mealService.findByNameAndDescription(this.mealThatExistsDTO.getName(), this.mealThatExistsDTO.getDescription())).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMeal", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 
	 
	 @Test
	 public void addMeal_MealDoesNotExist_OK() {
		 given(this.mealService.findByNameAndDescription(this.newMealDTO.getName(), this.newMealDTO.getDescription())).willReturn(false);
		 Meal meal = Meal.builder().name(newMealDTO.getName()).description(newMealDTO.getDescription())
					.amountNumber(newMealDTO.getAmountNumber()).amountUnit(newMealDTO.getAmountUnit())
					.image(newMealDTO.getImage()).mealDifficulty(newMealDTO.getMealDifficulty()).timePreparation(newMealDTO.getTimePreparation())
					.type(newMealDTO.getType()).build();
		 given(this.mealService.addMeal(meal)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMealDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMeal", HttpMethod.POST, httpEntity, Boolean.class);

	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());    
	    assertTrue(responseEntity.getBody());
	 }
	 
	 
	 
	 @Test
	 public void changeMeal_MealDoesNotExist_NotFound() {
		 given(this.mealService.exists(this.mealThatDoenNotExistsDTO.getId())).willReturn(false);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatDoenNotExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMeal", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 @Test
	 public void changeMeal_EverythingOK_OK()  {
		 given(mealService.exists(mealThatExistsDTO.getId())).willReturn(true);
		 Meal meal = Meal.builder().name(mealThatExistsDTO.getName()).description(mealThatExistsDTO.getDescription())
					.amountNumber(mealThatExistsDTO.getAmountNumber()).amountUnit(mealThatExistsDTO.getAmountUnit())
					.image(mealThatExistsDTO.getImage()).mealDifficulty(mealThatExistsDTO.getMealDifficulty()).timePreparation(mealThatExistsDTO.getTimePreparation())
					.type(mealThatExistsDTO.getType()).build();
		 given(mealService.changeMeal(meal)).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMeal", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	 }
	 
	 
	 @Test
	 public void deleteMeal_EverythingOK_OK()  {
		 given(this.mealService.exists(this.mealThatExistsDTO.getId())).willReturn(true);
		 given(this.mealService.delete(this.mealThatExistsDTO.getId())).willReturn(true);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMeal", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 }
	 
	 
	 
	 @Test
	 public void deleteMeal_MealDoesNotExist_NotFound() {
		 given(this.mealService.exists(this.mealThatDoenNotExistsDTO.getId())).willReturn(false);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatDoenNotExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMeal", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	 }
	 
	
}
