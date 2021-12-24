package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.repository.DrinkPriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static com.backend.springboot.constants.DrinkConstants.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DrinkPriceServiceUnitTest {

    @Autowired
    private DrinkPriceService drinkPriceService;

    @MockBean
    private DrinkPriceRepository drinkPriceRepository;

    private DrinkPrice nesacuvanaCena;

    @Before
    public void setUpp() {
        DrinkPrice sacuvanaCena = DrinkPrice.builder().id(DRINK_PRICE_ID)
                .drink(Drink.builder().id(DRINK_ID).build()).price(DRINK_PRICE_PRICE).build();
        List<DrinkPrice> listaCena = new ArrayList<>();
        listaCena.add(sacuvanaCena);
        nesacuvanaCena = DrinkPrice.builder().price(DRINK_PRICE_PRICE).build();

        given(this.drinkPriceRepository.findAll()).willReturn(listaCena);
        given(this.drinkPriceRepository.findById(DRINK_PRICE_ID)).willReturn(java.util.Optional.of(sacuvanaCena));
        given(this.drinkPriceRepository.save(this.nesacuvanaCena)).willReturn(sacuvanaCena);
    }

    @Test
    public void findAll_EverythingOk_ReturnListDrinkPrice() {
        List<DrinkPrice> pronadjena = this.drinkPriceService.findAll();
        verify(this.drinkPriceRepository).findAll();
        assertEquals(ONE_DRINK_PRICE, pronadjena.size());
    }

    @Test
    public void findOne_EverythingOk_ReturnDrinkPrice() {
        DrinkPrice pronadjena = this.drinkPriceService.findOne(DRINK_PRICE_ID);
        verify(this.drinkPriceRepository).findById(DRINK_PRICE_ID);
        int id = pronadjena.getId();
        assertEquals(DRINK_PRICE_ID, id);
    }

    @Test
    public void findOne_NotExistId_ReturnNull() {
        DrinkPrice pronadjena = this.drinkPriceService.findOne(NOT_DRINK_PRICE_ID);
        verify(this.drinkPriceRepository).findById(NOT_DRINK_PRICE_ID);
        assertNull(pronadjena);
    }

    @Test
    public void save_EverythingOk_ReturnDrinkPrice() {
        DrinkPrice sacuvana = this.drinkPriceService.save(this.nesacuvanaCena);
        verify(this.drinkPriceRepository).save(this.nesacuvanaCena);
        int id = sacuvana.getId();
        assertEquals(DRINK_PRICE_ID, id);
    }
}
