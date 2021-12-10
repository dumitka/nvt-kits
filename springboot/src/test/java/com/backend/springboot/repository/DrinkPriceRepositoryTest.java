package com.backend.springboot.repository;

import com.backend.springboot.model.DrinkPrice;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.DrinkConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class DrinkPriceRepositoryTest {

    @Autowired
    private DrinkPriceRepository drinkPriceRepository;

    @Test
    public void findOneByDrinkIdTest() {
        DrinkPrice pronadjen = this.drinkPriceRepository.findOneByDrinkId(DRINK_ID);
        assertEquals(DRINK_NAME, pronadjen.getDrink().getName());
    }

}
