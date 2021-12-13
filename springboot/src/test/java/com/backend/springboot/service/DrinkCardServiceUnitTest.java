package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.repository.DrinkCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
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
public class DrinkCardServiceUnitTest {

    @Autowired
    private DrinkCardService drinkCardService;

    @MockBean
    private DrinkCardRepository drinkCardRepository;

    private DrinkCard kartaPica;

    @Before
    public void setUpp() {
        List<DrinkCard> sveKartePica = new ArrayList<>();
        DrinkPrice cena = DrinkPrice.builder().id(DRINK_PRICE_ID).drink(Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).build()).price(DRINK_PRICE_PRICE).build();
        Set<DrinkPrice> listaCena = new HashSet();
        listaCena.add(cena);
        this.kartaPica = DrinkCard.builder().id(DRINK_CARD_ID)
                .dateOfValidation(TIME).
                drinkPrices(listaCena).build();
        sveKartePica.add(kartaPica);

        given(this.drinkCardRepository.findAll()).willReturn(sveKartePica);

        this.kartaPica = DrinkCard.builder().dateOfValidation(TIME).
                drinkPrices(listaCena).build();
        DrinkCard sacuvanaKP = DrinkCard.builder().dateOfValidation(TIME).
                drinkPrices(listaCena).id(DRINK_CARD_ID).build();

        given(this.drinkCardRepository.findById(DRINK_CARD_ID)).willReturn(java.util.Optional.of(sacuvanaKP));
        given(this.drinkCardRepository.save(this.kartaPica)).willReturn(sacuvanaKP);

    }

    @Test
    public void findAllEverythingOk_ReturnListDrinkCard() {
        List<DrinkCard> pronadjena = this.drinkCardService.findAll();
        verify(this.drinkCardRepository).findAll();
        assertEquals(DRINK_CARD_NUMBER, pronadjena.size());
    }

    @Test
    public void findOne_EverythingOk_ReturnDrinkCard() {
        DrinkCard pronadjena = this.drinkCardService.findOne(DRINK_CARD_ID);
        verify(this.drinkCardRepository).findById(DRINK_CARD_ID);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void findOne_NotExistId_ReturnNull() {
        DrinkCard pronadjena = this.drinkCardService.findOne(NOT_DRINK_ID);
        verify(this.drinkCardRepository).findById(NOT_DRINK_ID);
        assertNull(pronadjena);
    }

    @Test
    public void save_EverythingOk_ReturnDrinkCard() {
        DrinkCard pronadjena = this.drinkCardService.save(this.kartaPica);
        verify(this.drinkCardRepository).save(kartaPica);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void findLatest_EverythingOk_ReturnDrinkCard() {
        DrinkCard pronadjena = this.drinkCardService.findLatest();
        verify(this.drinkCardRepository).findAll();
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void removeDrink_EverythingOk_ReturnTrue() {
        DrinkPrice cena = DrinkPrice.builder().drink(Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).build()).price(DRINK_PRICE_PRICE).build();
        boolean izbrisano = drinkCardService.removeDrink(cena.getDrink());
        verify(drinkCardRepository).findAll();
        assertTrue(izbrisano);
        // mozda ubaciti kod za dodavanje cene pica nazad u listu
    }

    @Test
    public void removeDrink_NotExistDrinkId_ReturnFalse() {
        Drink pice = Drink.builder().id(NOT_DRINK_ID).build();
        boolean izbrisano = drinkCardService.removeDrink(pice);
        verify(drinkCardRepository).findAll();
        assertFalse(izbrisano);
    }

    @Test
    public void findPriceOfDrinkForDate_EverythingOk_ReturnDrinkPrice() {
        DrinkPrice pronadjena = this.drinkCardService.findPriceOfDrinkForDate(LocalDateTime.now(), DRINK_ID);
        verify(drinkCardRepository).findAll();
        int id = pronadjena.getId();
        assertEquals(DRINK_PRICE_ID, id);
    }

    @Test
    public void findPriceOfDrinkForDate_NotExistDrinkId_ReturnNull() {
        DrinkPrice pronadjena = this.drinkCardService.findPriceOfDrinkForDate(LocalDateTime.now(), NOT_DRINK_ID);
        verify(drinkCardRepository).findAll();
        assertNull(pronadjena);
    }
}
