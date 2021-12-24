package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import com.backend.springboot.repository.DrinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.DrinkConstants.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DrinkServiceUnitTest {

    @Autowired
    private DrinkService drinkService;

    @MockBean
    private DrinkRepository drinkRepository;

    private Drink nesacuvanoPice;

    @Before
    public void setUpp() {
        this.nesacuvanoPice = Drink.builder().name(DRINK_NAME).available(true).build();
        Drink sacuvanoPice = Drink.builder().id(DRINK_ID).name(DRINK_NAME).available(true)
                .amountUnit(DRINK_AMOUNT_UNIT).amountNumber(DRINK_AMOUNT_NUMBER).build();
        List<Drink> svaPica = new ArrayList<>();
        svaPica.add(sacuvanoPice);

        given(this.drinkRepository.findAll()).willReturn(svaPica);
        given(this.drinkRepository.findById(DRINK_ID)).willReturn(java.util.Optional.of(sacuvanoPice));
        given(this.drinkRepository.save(this.nesacuvanoPice)).willReturn(sacuvanoPice);
    }

    @Test
    public void findAll_EverythingOk_ReturnListDrink() {
        List<Drink> pronadjena = this.drinkService.findAll();
        verify(this.drinkRepository).findAll();
        assertEquals(ONE_DRINK, pronadjena.size());
    }

    @Test
    public void findAllAvailable_EverythingOk_ReturnListDrink() {
        List<Drink> pronadjena = this.drinkService.findAllAvailable();
        verify(this.drinkRepository).findAll();
        assertEquals(ONE_DRINK, pronadjena.size());
    }

    @Test
    public void findOne_EverythingOk_ReturnDrink() {
        Drink pronadjeno = this.drinkService.findOne(DRINK_ID);
        verify(this.drinkRepository).findById(DRINK_ID);
        int id = pronadjeno.getId();
        assertEquals(DRINK_ID, id);
    }

    @Test
    public void findOne_NotExistId_ReturnNull() {
        Drink pronadjeno = this.drinkService.findOne(NOT_DRINK_ID);
        verify(this.drinkRepository).findById(NOT_DRINK_ID);
        assertNull(pronadjeno);
    }

    @Test
    public void freeNameAndAmount_NotExistName_ReturnTrue() {
        boolean potvrda = this.drinkService.freeNameAndAmount(NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        verify(this.drinkRepository).findAll();
        assertTrue(potvrda);
    }

    @Test
    public void freeNameAndAmount_ExistAll_ReturnFalse() {
        boolean potvrda = this.drinkService.freeNameAndAmount(DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        verify(this.drinkRepository).findAll();
        System.out.println(potvrda);
        assertFalse(potvrda);
    }

    @Test
    public void editableDrink_EverythingOk_ReturnTrue() {
        boolean potvrda = this.drinkService.editableDrink(DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        verify(this.drinkRepository).findAll();
        assertTrue(potvrda);
    }

    @Test
    public void editableDrink_NotExistName_ReturnTrue() {
        boolean potvrda = this.drinkService.editableDrink(DRINK_ID, NOT_DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        verify(this.drinkRepository).findAll();
        assertTrue(potvrda);
    }

    @Test
    public void editableDrink_NotExistId_ReturnFalse() {
        boolean potvrda = this.drinkService.editableDrink(NOT_DRINK_ID, DRINK_NAME, DRINK_AMOUNT_UNIT, DRINK_AMOUNT_NUMBER);
        verify(this.drinkRepository).findAll();
        assertFalse(potvrda);
    }

    @Test
    public void findByName_EverythingOk_ReturnListDrink() {
        List<Drink> pronadjena = this.drinkService.findByName(DRINK_NAME);
        verify(this.drinkRepository).findAll();
        assertEquals(ONE_DRINK, pronadjena.size());
    }

}
