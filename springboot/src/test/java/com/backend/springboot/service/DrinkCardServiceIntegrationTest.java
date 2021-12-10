package com.backend.springboot.service;

import com.backend.springboot.enums.DrinkType;
import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.springboot.constants.DrinkCardConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class DrinkCardServiceIntegrationTest {

    @Autowired
    private DrinkCardService drinkCardService;
    @Test
    public void findAllTest() {
        List<DrinkCard> pronadjena = this.drinkCardService.findAll();
        assertEquals(ITEM_NUMBER, pronadjena.size());
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
        DrinkPrice cena = DrinkPrice.builder().drink(Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).build()).price(DRINK_PRICE).build();
        Set listaCena = new HashSet();
        listaCena.add(cena);
        DrinkCard kartaPica = DrinkCard.builder().dateOfValidation(TIME).
                drinkPrices(listaCena).build();

        DrinkCard pronadjena = this.drinkCardService.save(kartaPica);
        assertNotNull(pronadjena);
        int id = pronadjena.getId();
        assertEquals(DRINK_CARD_ID, id);
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
        Drink pice = Drink.builder()
                .id(DRINK_ID).name(DRINK_NAME).type(DrinkType.HOT_DRINK).description("Milfordov caj")
                .image("caj.jpg").amountNumber(0.2).amountUnit("l").available(true).build();
        DrinkPrice cena = DrinkPrice.builder().drink(pice).price(DRINK_PRICE).build();
        boolean izbrisano = drinkCardService.removeDrink(cena.getDrink());
        assertTrue(izbrisano);
    }
}
