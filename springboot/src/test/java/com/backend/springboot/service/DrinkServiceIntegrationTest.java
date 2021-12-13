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
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkServiceIntegrationTest {

    @Autowired
    private DrinkService drinkService;

    @Test
    public void findAll_EverythingOk_ReturnListDrink() {
        List<Drink> pronadjena = this.drinkService.findAll();
        assertEquals(DRINK_NUMBER, pronadjena.size());
    }

    @Test
    public void findAllAvailable_EverythingOk_ReturnListDrink() {
        List<Drink> pronadjena = this.drinkService.findAllAvailable();
        assertEquals(DRINK_NUMBER, pronadjena.size());
    }

    @Test
    public void findOne_EverythingOk_ReturnDrink() {
        Drink pronadjeno = this.drinkService.findOne(DRINK_ID);
        int id = pronadjeno.getId();
        assertEquals(DRINK_ID, id);
    }

    @Test
    public void findOne_NotExistId_ReturnNull() {
        Drink pronadjeno = this.drinkService.findOne(NOT_DRINK_ID);
        assertNull(pronadjeno);
    }

    @Test
    public void freeNameAndAmount_NotExistName_ReturnTrue() {
        boolean potvrda = this.drinkService.freeNameAndAmount(NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertTrue(potvrda);
    }

    @Test
    public void freeNameAndAmount_ExistAll_ReturnFalse() {
        boolean potvrda = this.drinkService.freeNameAndAmount(DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertFalse(potvrda);
    }

    @Test
    public void editableDrink_EverythingOk_ReturnTrue() {
        boolean potvrda = this.drinkService.editableDrink(DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertTrue(potvrda);
    }

    @Test
    public void editableDrink_NotExistName_ReturnTrue() {
        boolean potvrda = this.drinkService.editableDrink(DRINK_ID, NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertTrue(potvrda);
    }

    @Test
    public void editableDrink_NotExistId_ReturnFalse() {
        boolean potvrda = this.drinkService.editableDrink(NOT_DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        assertFalse(potvrda);
    }

    @Test
    public void findByName_EverythingOk_ReturnListDrink() {
        List<Drink> pronadjena = this.drinkService.findByName(DRINK_NAME);
        assertEquals(ONE_DRINK, pronadjena.size());
    }
}
