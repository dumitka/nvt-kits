package com.backend.springboot.controller;

import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.*;
import com.backend.springboot.service.NotificationService;
import com.backend.springboot.service.OrderService;
import com.backend.springboot.service.OrderedMealService;
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

import java.util.ArrayList;
import java.util.List;

import static com.backend.springboot.constants.OrderedMealConstants.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrderedMealContollerUnitTest {

    private static final String URL_PREFIX = "/api/orderedMeals";

    @MockBean
    private OrderedMealService orderedMealService;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private OrderService orderService;

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

        OrderedMeal narucenoJelo = OrderedMeal.builder().id(ID_1).amount(5).status(OrderedItemStatus.ORDERED)
                .meal(Meal.builder().id(1).build()).order(Order.builder().id(1).build())
                .cook(User.builder().id(USER_ID).build()).build();
        List<OrderedMeal> lista = new ArrayList<>();
        lista.add(narucenoJelo);

        given(this.orderedMealService.findByStatus(OrderedItemStatus.ORDERED)).willReturn(lista);
        given(this.orderedMealService.findOne(ID_1)).willReturn(narucenoJelo);
        given(this.orderedMealService.findByCookIdAndStatus(USER_ID, OrderedItemStatus.ORDERED)).willReturn(lista);
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
        assertEquals(1, lista.length);
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
