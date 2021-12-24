package com.backend.springboot.controller;

import com.backend.springboot.dto.*;
import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.DrinkPriceService;
import com.backend.springboot.service.DrinkService;
import com.backend.springboot.service.RestaurantService;
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

import static org.junit.Assert.*;
import static com.backend.springboot.constants.DrinkCardConstants.*;
import static com.backend.springboot.constants.DrinkConstants.*;
import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static com.backend.springboot.constants.RestaurantConstants.*;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkCardControllerUnitTest {

    private static final String URL_PREFIX = "/api/drinkCards/";

    @MockBean
    private DrinkCardService drinkCardService;

    @MockBean
    private DrinkPriceService drinkPriceService;

    @MockBean
    private DrinkService drinkService;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    @Before
    public void login() {
        JwtAuthenticationRequest prijava = JwtAuthenticationRequest.builder().username("sef-sale")
                .password("pera").build();
        ResponseEntity<UserTokenState> responseEntity =
                this.restTemplate.postForEntity("/auth/login",
                        prijava,
                        UserTokenState.class);
        this.accessToken = responseEntity.getBody().getAccessToken();

        Restaurant restoran = Restaurant.builder().id(RESTAURANT_ID).build();
        Drink pice = Drink.builder().id(DRINK_ID).name(DRINK_NAME).build();
        DrinkPrice cenaPica = DrinkPrice.builder().id(DRINK_PRICE_ID).drink(pice).drinkCards(new HashSet<>()).price(DRINK_PRICE_PRICE).build();
        Set<DrinkPrice> sveCene = new HashSet<>();
        sveCene.add(cenaPica);
        DrinkCard kartaPica = DrinkCard.builder().id(DRINK_CARD_ID).restaurant(restoran).drinkPrices(sveCene).build();

        given(this.restaurantService.findOne(RESTAURANT_ID)).willReturn(restoran);
        given(this.drinkService.findOne(DRINK_ID)).willReturn(pice);
        given(this.drinkPriceService.save(cenaPica)).willReturn(null);
        given(this.drinkPriceService.findByDrinkAndPrice(DRINK_ID, DRINK_PRICE_PRICE)).willReturn(cenaPica);
        given(this.drinkCardService.findLatest()).willReturn(kartaPica);
        given(this.drinkCardService.save(kartaPica)).willReturn(null);
    }

    @Test
    public void gettingDrinkCard_EverythingOk_ReturnDrinkCardDTO() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        headers.add("Authorization", "Bearer " + this.accessToken);
        ResponseEntity<DrinkCard> responseEntity =
                this.restTemplate.exchange(URL_PREFIX, HttpMethod.GET, httpEntity, DrinkCard.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DrinkCard kartaPica = responseEntity.getBody();
        assertNotNull(kartaPica);
        int id = kartaPica.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void newDrinkCard_EverythingOk_ReturnDrinkCardDTO() {
        DrinkDTO pice = DrinkDTO.builder().id(DRINK_ID).name(DRINK_NAME).build();
        DrinkPriceDTO cenaPica = DrinkPriceDTO.builder().id(DRINK_PRICE_ID).drinkDTO(pice).price(DRINK_PRICE_PRICE).build();
        Set<DrinkPriceDTO> sveCene = new HashSet<>();
        sveCene.add(cenaPica);
        DrinkCardDTO stara = DrinkCardDTO.builder().restaurantId(RESTAURANT_ID).drinkPriceDTOs(sveCene).build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(stara, headers);

        ResponseEntity<DrinkCardDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "newDrinkCard", HttpMethod.POST, httpEntity, DrinkCardDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DrinkCardDTO nova = responseEntity.getBody();
        assertNotNull(nova);
        assertNull(nova.getId());
        int id = nova.getDrinkPriceDTOs().stream().toList().get(0).getId();
        assertEquals(DRINK_PRICE_ID, id);
        assertEquals(ONE_DRINK_PRICE, nova.getDrinkPriceDTOs().size());
    }
}
