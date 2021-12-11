package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.DrinkCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.springboot.constants.DrinkConstants.*;
import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static com.backend.springboot.constants.DrinkCardConstants.*;
import static com.backend.springboot.constants.RestaurantConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkCardServiceIntegrationTest {

    @Autowired
    private DrinkCardService drinkCardService;

    @Autowired
    private DrinkCardRepository drinkCardRepository;

    @Test
    public void findAllTest() {
        List<DrinkCard> pronadjena = this.drinkCardService.findAll();
        assertEquals(DRINK_CARD_NUMBER, pronadjena.size());
    }

    @Test
    public void findOneTest() {
        DrinkCard pronadjena = this.drinkCardService.findOne(DRINK_CARD_ID);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void saveTest() {
        Drink pice = Drink.builder().id(DRINK_ID).name(DRINK_NAME).build();
        Restaurant restoran = Restaurant.builder().id(RESTAURANT_ID).build();
        DrinkPrice cena = DrinkPrice.builder().id(DRINK_PRICE_ID).drink(pice).price(DRINK_PRICE_PRICE).build();
        Set listaCena = new HashSet();
        listaCena.add(cena);
        DrinkCard kartaPica = DrinkCard.builder()
                .dateOfValidation(TIME).drinkPrices(listaCena).restaurant(restoran).build();

        DrinkCard pronadjena = this.drinkCardService.save(kartaPica);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(NEW_DC_ID, id);
        this.drinkCardRepository.delete(pronadjena);
    }

    @Test
    public void findLatestTest() {
        DrinkCard pronadjena = this.drinkCardService.findLatest();
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
    }

    @Test
    public void removeDrinkTest() {
        Drink pice = Drink.builder().id(DRINK_ID).build();
        boolean izbrisano = drinkCardService.removeDrink(pice);
        assertTrue(izbrisano);
    }

    @Test
    public void failRemoveDrinkNotDrinkIdTest() {
        Drink pice = Drink.builder().id(NOT_DRINK_ID).build();
        boolean izbrisano = drinkCardService.removeDrink(pice);
        assertFalse(izbrisano);
    }
}