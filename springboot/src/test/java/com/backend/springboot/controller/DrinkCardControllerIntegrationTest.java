package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkCardDTO;
import com.backend.springboot.dto.DrinkDTO;
import com.backend.springboot.dto.DrinkPriceDTO;
import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.DrinkPriceService;
import com.backend.springboot.service.DrinkService;
import com.backend.springboot.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.HashSet;
import java.util.Set;

import static com.backend.springboot.constants.DrinkCardConstants.*;
import static com.backend.springboot.constants.DrinkConstants.*;
import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static com.backend.springboot.constants.RestaurantConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkCardControllerIntegrationTest {

    private static final String URL_PREFIX = "/api/drinkCards/";

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    @Before
    public void login() {
        JwtAuthenticationRequest prijava = JwtAuthenticationRequest.builder().username("sef-sale")
                .password("pera").build();
        ResponseEntity<String> responseEntity =
                this.restTemplate.postForEntity("/auth/login",
                        prijava,
                        String.class);
        this.accessToken = responseEntity.getBody();
        System.out.println(this.accessToken);
    }

    @Test
    public void gettingDrinkCard_EverythingOk_ReturnDrinkCardDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<DrinkCard> responseEntity =
                this.restTemplate.exchange(URL_PREFIX, HttpMethod.GET, httpEntity, DrinkCard.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        int id = responseEntity.getBody().getId();
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
        headers.add("X-Auth-Token", this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(stara, headers);

        ResponseEntity<DrinkCardDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "newDrinkCard", HttpMethod.POST, httpEntity, DrinkCardDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DrinkCardDTO nova = responseEntity.getBody();
        int id = nova.getId();
        assertEquals(NEW2_DC_ID, id);
        id = nova.getDrinkPriceDTOs().stream().toList().get(0).getId();
        assertEquals(DRINK_PRICE_ID, id);
        assertEquals(ONE_DRINK_PRICE, nova.getDrinkPriceDTOs().size());
    }
}
