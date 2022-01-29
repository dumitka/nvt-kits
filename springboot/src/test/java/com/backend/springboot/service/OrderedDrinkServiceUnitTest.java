package com.backend.springboot.service;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.repository.OrderedDrinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.backend.springboot.constants.OrderedDrinkConstants.DRINK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderedDrinkServiceUnitTest {

    @MockBean
    private OrderedDrinkRepository repository;

    @Autowired
    private OrderedDrinkService service;

    @Test
    public void findByStatus_ok_returnDrinks (){
        when(repository.findByStatus(OrderedItemStatus.ORDERED)).thenReturn(Arrays.asList(DRINK));

        List<OrderedDrink> drinks = service.findByStatus(OrderedItemStatus.ORDERED);

        assertEquals(1, drinks.size());
        assertEquals(drinks.get(0).getId(), DRINK.getId());

    }

    @Test
    public void findByBartenderIdAndStatus_ok_returnDrinks() {
        when(repository.findByBartenderIdAndStatus(DRINK.getBartender().getId(),OrderedItemStatus.ORDERED))
                .thenReturn(Arrays.asList(DRINK));

        List<OrderedDrink> drinks = service.findByBartenderIdAndStatus(DRINK.getBartender().getId(),OrderedItemStatus.ORDERED);

        assertEquals(1, drinks.size());
        assertEquals(DRINK.getId(), drinks.get(0).getId());
    }

}
