package com.backend.springboot.controller;

import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.dto.UserTokenState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.OrderedMealConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrderedMealContollerIntegrationTest {

    private static final String URL_PREFIX = "/api/orderedMeals";

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    @Before
    public void login() {
        JwtAuthenticationRequest prijava = JwtAuthenticationRequest.builder().username("kuvar")
                .password("pera").build();
        System.out.println(this.restTemplate);
        ResponseEntity<UserTokenState> responseEntity =
                this.restTemplate.postForEntity("/auth/login",
                        prijava,
                        UserTokenState.class);
        this.accessToken = responseEntity.getBody().getAccessToken();
    }


    @Test
    public void notAcceptedOrderedMeals_EverythingOk_ReturnListOrderedMealDTO() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        headers.add("Authorization", "Bearer " + this.accessToken);
        ResponseEntity<OrderedMealDTO[]> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "/notAccepted", HttpMethod.GET, httpEntity, OrderedMealDTO[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        OrderedMealDTO[] lista = responseEntity.getBody();
        assertEquals(4, lista.length);
    }

    @Test
    public void acceptOrderedMeal_EverythingOk_ReturnTrue() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(ID_1, headers);
        headers.add("Authorization", "Bearer " + this.accessToken);
        ResponseEntity<Boolean> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "/acceptMeal", HttpMethod.PUT, httpEntity, Boolean.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());
    }

    @Test
    public void acceptedOrderedMeals_EverythingOk_ReturnListOrderedMealDTO() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        headers.add("Authorization", "Bearer " + this.accessToken);
        ResponseEntity<OrderedMealDTO[]> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "/accepted/"+USER_ID, HttpMethod.GET, httpEntity, OrderedMealDTO[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        OrderedMealDTO[] lista = responseEntity.getBody();
        assertEquals(0, lista.length);
    }

    @Test
    public void finishOrderedMeal_EverythingOk_ReturnTrue() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(ID_1, headers);
        headers.add("Authorization", "Bearer " + this.accessToken);
        ResponseEntity<Boolean> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "/finishMeal", HttpMethod.PUT, httpEntity, Boolean.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());
    }
}
