package com.backend.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

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
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.dtoTransformation.MealPriceToMealWithPriceDTO;
import com.backend.springboot.dtoTransformation.MealToMealDTO;
import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.exception.MealAlreadyExistsException;
import com.backend.springboot.exception.MealDoesNotExist;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MealService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealControllerIntegrationTest {
 private static final String URL_PREFIX = "/meal/";
	 
 	 @Autowired
	 private MealPriceService mealPriceService;
	 
	 @Autowired
	 private MealService mealService;
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 private String accessToken;
	 

	 //params
	 private MealDTO mealThatExistsDTO;
	 private MealDTO mealThatDoenNotExistsDTO;
	 
	 
	 
	 
	 
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
	       mealThatExistsDTO = MealDTO.builder().id(1).name("Kajgana").description("Domace, zdravo").amountNumber(200).amountUnit("g")
	    		   .deleted(false).image("nema").mealDifficulty(MealDifficulty.EASY).timePreparation(5).type(MealType.COLD_APPETIZER)
	    		   .build();
	       mealThatDoenNotExistsDTO = MealDTO.builder().id(100).name("Novo jelo").description("Opis novog jela").amountNumber(100).amountUnit("g")
	    		   .deleted(false).image("image").mealDifficulty(MealDifficulty.EASY).timePreparation(60).type(MealType.DESERT)
	    		   .build();
	       
	       
	 }

	 
	 @Test
	 public void getColdAppetizers_EverythingOK_ListOfMealPrices() {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getColdAppetizers", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);
	                
	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     
	 }
	 
	 
	 @Test
	 public void getHotAppetizer_EverythingOK_ListOfMealPrices() {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
	     ResponseEntity<MealWithPriceDTO[]> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "getHotAppetizer", HttpMethod.GET, httpEntity, MealWithPriceDTO[].class);
	                
	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     
	 }
	
	 
	 
	 @Test
	 public void addMeal_MealAlreadyExistsException_BadRequest() {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMeal", HttpMethod.POST, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	     
	 }
	 
	 
	 
	 
	 @Test
	 public void addMeal_EverythingOK_OK()  {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatDoenNotExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "addMeal", HttpMethod.POST, httpEntity, Boolean.class);

	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertTrue(responseEntity.getBody());
	     
	 }
	 
	 
	 
	 @Test
	 public void changeMeal_MealDoesNotExist_BadRequest() {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatDoenNotExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMeal", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     
	 }
	 
	 
	 @Test
	 public void changeMeal_EverythingOK_OK()  {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "changeMeal", HttpMethod.PUT, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    
	 }
	 
	 
	 @Test
	 public void deleteMeal_EverythingOK_OK()  {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMeal", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     
	 }
	 
	 
	 
	 @Test
	 public void deleteMeal_MealDoesNotExist_BadRequest()  {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatDoenNotExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMeal", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	  
	 }
	 
	 
	 
}
