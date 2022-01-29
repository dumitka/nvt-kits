package com.backend.springboot.repository;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedDrink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static com.backend.springboot.constants.UserConstans.*;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class OrderedDrinkRepositoryTest {

    @Autowired
    private OrderedDrinkRepository repository;

    @Test
    public void findByStatus() {
        List<OrderedDrink> drinks = repository.findByStatus(OrderedItemStatus.ORDERED);
        assertEquals(drinks.size(), 1);
    }
    @Test
    public void findByBartenderId() {
        List<OrderedDrink> drinks = repository.findByBartenderId(BARTENDER_ID);
        assertEquals(drinks.size(), 2);
    }

}
