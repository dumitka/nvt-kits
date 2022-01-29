package com.backend.springboot.repository;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedMeal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.backend.springboot.constants.OrderedMealConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class OrderedMealRepositoryTest {

    @Autowired
    private OrderedMealRepository orderedMealRepository;

    @Test
    public void findOneByNotificationId_EverythingOk_ReturnOrderedMeal() {
        OrderedMeal pronadjen = this.orderedMealRepository.findOneById(ID_1);
        assertTrue(pronadjen.getAmount() == AMOUNT_2);
    }

    @Test
    public void findByStatus_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjenaLista = this.orderedMealRepository.findByStatus(OrderedItemStatus.ORDERED);
        assertEquals(ITEM_NUMBER, pronadjenaLista.size());
    }

    @Test
    public void findByCookIdAndStatus_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjenaLista = this.orderedMealRepository.findByCookIdAndStatus(USER_ID, OrderedItemStatus.ORDERED);
        assertEquals(ITEM_NUMBER, pronadjenaLista.size());
    }
}
