package com.backend.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealControllerIntegrationTest {
 private static final String URL_PREFIX = "/meal/";
	 
 	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 private String accessToken;
	 

	 //params
	 private MealDTO mealThatExistsDTO;
	 private MealDTO mealThatDoenNotExistsDTO;
	 private MealDTO newMeal;
	 private MealDTO deleteMeal;
	 
	 
	 
	 
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
	       this.mealThatDoenNotExistsDTO = MealDTO.builder().id(100).name("Novo jelo").description("Opis novog jela").amountNumber(100).amountUnit("g")
	    		   .deleted(false).image("image").mealDifficulty(MealDifficulty.EASY).timePreparation(60).type(MealType.DESERT)
	    		   .build();
	       this.newMeal = MealDTO.builder().name("Novo jelo").description("Opis novog jela").amountNumber(100).amountUnit("g")
	    		   .deleted(false).image("image").mealDifficulty(MealDifficulty.EASY).timePreparation(60).type(MealType.DESERT)
	    		   .build();
	       this.deleteMeal = MealDTO.builder().id(5).name("Supa").type(MealType.HOT_APPETIZER).deleted(false)
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
	     assertFalse(responseEntity.getBody());
	     
	 }
	 
	 
	 
	 
	 @Test
	 public void addMeal_EverythingOK_OK()  {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.newMeal, headers);
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
	     assertFalse(responseEntity.getBody());
	     
	 }
	 
	 
	 @Test
	 public void changeMeal_EverythingOK_OK()  {
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
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.deleteMeal, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMeal", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	     assertTrue(responseEntity.getBody());
	     
	 }
	 
	 
	 
	 @Test
	 public void deleteMeal_MealDoesNotExist_BadRequest()  {
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Authorization", "Bearer " + this.accessToken);
	     HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.mealThatDoenNotExistsDTO, headers);
	     ResponseEntity<Boolean> responseEntity =
	                this.restTemplate.exchange(URL_PREFIX + "deleteMeal", HttpMethod.DELETE, httpEntity, Boolean.class);

	     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	     assertFalse(responseEntity.getBody());
	 }
	 
	 
	 
}
