package com.backend.springboot.controller;

import com.backend.springboot.dto.*;
import com.backend.springboot.dtoTransformation.MealPriceToMealWithPriceDTO;
import com.backend.springboot.dtoTransformation.MealToMealDTO;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.service.MealPriceService;
import com.backend.springboot.service.MenuService;
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

import java.time.LocalDateTime;
import java.util.Set;

import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU;
import static com.backend.springboot.constants.MenuConstants.*;
import static com.backend.springboot.constants.MenuConstants.CURRENT_MENU;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrderedDrinkControllerTest {

    private static final String URL_PREFIX = "/orderedDrinks/";


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



    }



//    @Test



}
