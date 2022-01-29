package com.backend.springboot.service;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.model.User;
import com.backend.springboot.repository.OrderedMealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.backend.springboot.constants.OrderedMealConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrderedMealServiceIntegrationTest {


    @Autowired
    private OrderedMealService orderedMealService;

    @Autowired
    private OrderedMealRepository orderedMealRepository;

    @Test
    public void findOne_EverythingOk_ReturnOrderedMeal() {
        OrderedMeal pronadjena = this.orderedMealService.findOne(ID_1);
        assertTrue(USER_ID == pronadjena.getCook().getId());
    }

    @Test
    public void findByStatus_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjena = this.orderedMealService.findByStatus(OrderedItemStatus.ORDERED);
        assertEquals(ITEM_NUMBER, pronadjena.size());
    }

    @Test
    public void findByCook_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjena = this.orderedMealService.findByCook(USER_ID);
        assertEquals(ITEM_NUMBER, pronadjena.size());
    }

    @Test
    public void findByCookIdAndStatus_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjena = this.orderedMealService.findByCookIdAndStatus(USER_ID, OrderedItemStatus.ORDERED);
        assertEquals(ITEM_NUMBER, pronadjena.size());
    }

    @Test
    public void save_EverythingOk_ReturnOrderedMeal() {
        OrderedMeal zaCuvanje = OrderedMeal.builder().amount(5).status(OrderedItemStatus.ORDERED)
                .meal(Meal.builder().id(1).build()).order(Order.builder().id(1).build())
                .cook(User.builder().id(USER_ID).build()).build();
        OrderedMeal pronadjena = this.orderedMealService.save(zaCuvanje);
        assertTrue(pronadjena.getId() == ID_NOV);
        this.orderedMealRepository.delete(pronadjena);
    }
}
