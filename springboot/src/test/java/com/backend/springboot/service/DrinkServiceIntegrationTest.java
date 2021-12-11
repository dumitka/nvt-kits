package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.backend.springboot.constants.DrinkConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkServiceIntegrationTest {

    @Autowired
    private DrinkService drinkService;

    @Test
    public void findAllTest() {
        List<Drink> pronadjena = this.drinkService.findAll();
        assertEquals(DRINK_NUMBER, pronadjena.size());
    }

    @Test
    public void findAllAvailableTest() {
        List<Drink> pronadjena = this.drinkService.findAllAvailable();
        assertEquals(DRINK_NUMBER, pronadjena.size());
    }

    @Test
    public void findOneTest() {
        Drink pronadjeno = this.drinkService.findOne(DRINK_ID);
        int id = pronadjeno.getId();
        assertEquals(DRINK_ID, id);
    }

    @Test
    public void freeNameAndAmountTest() {
        boolean potvrda = this.drinkService.freeNameAndAmount(NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertTrue(potvrda);
    }

    @Test
    public void notFreeNameAndAmountTest() {
        boolean potvrda = this.drinkService.freeNameAndAmount(DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertFalse(potvrda);
    }

    @Test
    public void editableDrinkTest() {
        boolean potvrda = this.drinkService.editableDrink(DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertTrue(potvrda);
    }

    @Test
    public void editableDrinkNotNameTest() {
        boolean potvrda = this.drinkService.editableDrink(DRINK_ID, NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertTrue(potvrda);
    }

    @Test
    public void failEditableDrinkNotIdTest() {
        boolean potvrda = this.drinkService.editableDrink(NOT_DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertFalse(potvrda);
    }

    @Test
    public void findByNameTest() {
        List<Drink> pronadjena = this.drinkService.findByName(DRINK_NAME);
        assertEquals(ONE_DRINK, pronadjena.size());
    }
}
