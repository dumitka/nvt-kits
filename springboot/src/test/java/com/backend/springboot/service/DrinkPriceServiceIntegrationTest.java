package com.backend.springboot.service;

import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.repository.DrinkPriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static com.backend.springboot.constants.DrinkPriceConstrants.DRINK_PRICE_ID;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkPriceServiceIntegrationTest {

    @Autowired
    private DrinkPriceService drinkPriceService;

    @Autowired
    private DrinkPriceRepository drinkPriceRepository;

    @Test
    public void findAllTest() {
        List<DrinkPrice> pronadjena = this.drinkPriceService.findAll();
        assertEquals(DRINK_PRICE_NUMBER, pronadjena.size());
    }

    @Test
    public void findOneTest() {
        DrinkPrice pronadjena = this.drinkPriceService.findOne(DRINK_PRICE_ID);
        int id = pronadjena.getId();
        assertEquals(DRINK_PRICE_ID, id);
    }

    @Test
    public void saveTest() {
        DrinkPrice sacuvana = this.drinkPriceService.save(DrinkPrice.builder().price(DRINK_PRICE_PRICE).build());
        int id = sacuvana.getId();
        assertEquals(NEW_DRINK_PRICE_ID, id);
        this.drinkPriceRepository.delete(sacuvana);
    }
}
