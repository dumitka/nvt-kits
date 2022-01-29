package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkDTO;
import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.UserTokenState;
import com.backend.springboot.enums.DrinkType;
import com.backend.springboot.model.Drink;
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.DrinkService;
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

import static com.backend.springboot.constants.DrinkConstants.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkControllerUnitTest {

    private static final String URL_PREFIX = "/api/drinks/";

    @MockBean
    private DrinkCardService drinkCardService;

    @MockBean
    private DrinkService drinkService;

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    private DrinkDTO piceSveOkDTO;
    private DrinkDTO piceNeIdDTO;
    private DrinkDTO piceNeImeDTO;

    @Before
    public void login() {
        JwtAuthenticationRequest prijava = JwtAuthenticationRequest.builder().username("sef-sale")
                .password("pera").build();
        ResponseEntity<UserTokenState> responseEntity =
                this.restTemplate.postForEntity("/auth/login",
                        prijava,
                        UserTokenState.class);
        this.accessToken = responseEntity.getBody().getAccessToken();

        Drink pice = Drink.builder().id(DRINK_ID).name(DRINK_NAME).type(DrinkType.HOT_DRINK).build();
        List<Drink> listaPica = new ArrayList<>();
        listaPica.add(pice);

        this.piceSveOkDTO = DrinkDTO.builder().id(DRINK_ID).name(DRINK_NAME).type(DrinkType.HOT_DRINK)
                .amountNumber(DRINK_AMOUNT_NUMBER).amountUnit(DRINK_AMOUNT_UNIT).build();
        this.piceNeIdDTO = DrinkDTO.builder().id(NOT_DRINK_ID).name(DRINK_NAME).type(DrinkType.HOT_DRINK)
                .amountNumber(DRINK_AMOUNT_NUMBER).amountUnit(DRINK_AMOUNT_UNIT).build();
        this.piceNeImeDTO = DrinkDTO.builder().id(DRINK_ID).name(NOT_DRINK_NAME).type(DrinkType.HOT_DRINK)
                .amountNumber(DRINK_AMOUNT_NUMBER).amountUnit(DRINK_AMOUNT_UNIT).build();

        given(this.drinkService.freeNameAndAmount(DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER)).willReturn(false);
        given(this.drinkService.freeNameAndAmount(NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER)).willReturn(true);
        given(this.drinkService.save(pice)).willReturn(null);
        given(this.drinkService.findOne(DRINK_ID)).willReturn(pice);
        given(this.drinkService.editableDrink(DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER))
                .willReturn(true);
        given(this.drinkService.editableDrink(DRINK_ID, NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER))
                .willReturn(true);
        given(this.drinkService.editableDrink(NOT_DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER))
                .willReturn(false);
        given(this.drinkService.findByName(DRINK_NAME)).willReturn(listaPica);
        given(this.drinkService.findAllAvailable()).willReturn(listaPica);
        given(this.drinkCardService.removeDrink(pice)).willReturn(true);
    }

    @Test
    public void addingNewDrink_EverythingOk_ReturnDrinkDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.piceNeImeDTO, headers);
        ResponseEntity<DrinkDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "addDrink", HttpMethod.POST, httpEntity, DrinkDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void editingDrink_NotEqualId_ReturnNull() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.piceNeIdDTO, headers);
        ResponseEntity<DrinkDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "editDrink", HttpMethod.POST, httpEntity, DrinkDTO.class);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
    @Test
    public void editingDrink_NotEqualName_ReturnDrink() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.piceNeImeDTO, headers);
        ResponseEntity<DrinkDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "editDrink", HttpMethod.POST, httpEntity, DrinkDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        int id = responseEntity.getBody().getId();
        assertEquals(DRINK_ID, id);
    }

    @Test
    public void editingDrink_ExistDrink_ReturnDrink() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.piceSveOkDTO, headers);
        ResponseEntity<DrinkDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "editDrink", HttpMethod.POST, httpEntity, DrinkDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        int id = responseEntity.getBody().getId();
        assertEquals(DRINK_ID, id);
    }

    @Test
    public void deletingDrink_EverythingOk_ReturnDrinkDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(this.piceSveOkDTO, headers);
        ResponseEntity<DrinkDTO> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "deleteDrink", HttpMethod.POST, httpEntity, DrinkDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        int id = responseEntity.getBody().getId();
        assertEquals(DRINK_ID, id);
    }

    @Test
    public void searchingDrinks_EverythingOk_ReturnListDrinkDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<DrinkDTO[]> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "searchDrinks/" + DRINK_NAME,
                        HttpMethod.GET, httpEntity, DrinkDTO[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DrinkDTO[] niz = responseEntity.getBody();
        assertNotNull(niz);
        assertEquals(ONE_DRINK,responseEntity.getBody().length);
    }

    @Test
    public void searchingDrinks_NotExistName_ReturnEmptyList() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<DrinkDTO[]> responseEntity =
                this.restTemplate.exchange(URL_PREFIX + "searchDrinks/" + NOT_DRINK_NAME,
                        HttpMethod.GET, httpEntity, DrinkDTO[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DrinkDTO[] niz = responseEntity.getBody();
        assertNotNull(niz);
        assertEquals(ZERO_DRINK,responseEntity.getBody().length);
    }

    @Test
    public void gettingDrinks_EverythingOk_ReturnListDrinkDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<DrinkDTO[]> responseEntity =
                this.restTemplate.exchange(URL_PREFIX, HttpMethod.GET, httpEntity, DrinkDTO[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DrinkDTO[] niz = responseEntity.getBody();
        assertNotNull(niz);
        assertEquals(ONE_DRINK, niz.length);
    }
}
