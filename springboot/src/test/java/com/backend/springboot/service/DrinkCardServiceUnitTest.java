package com.backend.springboot.service;

import com.backend.springboot.enums.DrinkType;
import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.DrinkCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.springboot.constants.DrinkCardConstants.*;
import static com.backend.springboot.constants.DrinkConstants.*;
import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkCardServiceUnitTest {

    @Autowired
    private DrinkCardService drinkCardService;

    @MockBean
    private DrinkCardRepository drinkCardRepository;

    @Before
    public void setUpp() {
        List<DrinkCard> sveKartePica = new ArrayList<>();
        DrinkPrice cena = DrinkPrice.builder().drink(Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).build()).price(DRINK_PRICE).build();
        Set listaCena = new HashSet();
        listaCena.add(cena);
        DrinkCard kartaPica = DrinkCard.builder().id(DRINK_CARD_ID)
                .dateOfValidation(TIME).
                drinkPrices(listaCena).build();
        sveKartePica.add(kartaPica);

        given(this.drinkCardRepository.findAll()).willReturn(sveKartePica);

        kartaPica = DrinkCard.builder().dateOfValidation(TIME).
                drinkPrices(listaCena).build();
        DrinkCard sacuvanaKP = DrinkCard.builder().dateOfValidation(TIME).
                drinkPrices(listaCena).id(DRINK_CARD_ID).build();

        given(this.drinkCardRepository.findById(DRINK_CARD_ID)).willReturn(java.util.Optional.of(sacuvanaKP));
        given(this.drinkCardRepository.save(kartaPica)).willReturn(sacuvanaKP);

    }

    @Test
    public void findAllTest() {
        List<DrinkCard> pronadjena = this.drinkCardService.findAll();
        verify(this.drinkCardRepository).findAll();
        assertEquals(ITEM_NUMBER, pronadjena.size());
    }

    @Test
    public void findOneTest() {
        DrinkCard pronadjena = this.drinkCardService.findOne(DRINK_CARD_ID);
        verify(this.drinkCardRepository).findById(DRINK_CARD_ID);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void saveTest() {
        DrinkPrice cena = DrinkPrice.builder().drink(Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).build()).price(DRINK_PRICE).build();
        Set listaCena = new HashSet();
        listaCena.add(cena);
        DrinkCard kartaPica = DrinkCard.builder().dateOfValidation(TIME).
                drinkPrices(listaCena).build();

        DrinkCard pronadjena = this.drinkCardService.save(kartaPica);
        verify(this.drinkCardRepository).save(kartaPica);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void findLatestTest() {
        DrinkCard pronadjena = this.drinkCardService.findLatest();
        verify(this.drinkCardRepository).findAll();
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void removeDrinkTest() {
        DrinkPrice cena = DrinkPrice.builder().drink(Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).build()).price(DRINK_PRICE).build();
        boolean izbrisano = drinkCardService.removeDrink(cena.getDrink());
        verify(drinkCardRepository).findAll();
        assertTrue(izbrisano);
    }
}
