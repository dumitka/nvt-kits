package com.backend.springboot.service;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.model.User;
import com.backend.springboot.repository.OrderedMealRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.backend.springboot.constants.OrderedMealConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderedMealServiceUnitTest {

    @Autowired
    private OrderedMealService orderedMealService;

    @MockBean
    private OrderedMealRepository orderedMealRepository;

    @Before
    public void setUpp() {
        OrderedMeal sacuvana = OrderedMeal.builder().id(ID_1).amount(5).status(OrderedItemStatus.ORDERED)
                .cook(User.builder().id(USER_ID).build()).build();
        List<OrderedMeal> lista = new ArrayList<>();
        lista.add(sacuvana);

        given(this.orderedMealRepository.findOneById(ID_1)).willReturn(sacuvana);
        given(this.orderedMealRepository.findByCookIdAndStatus(USER_ID, OrderedItemStatus.ORDERED)).willReturn(lista);
        given(this.orderedMealRepository.findByCookId(USER_ID)).willReturn(lista);
        given(this.orderedMealRepository.findByStatus(OrderedItemStatus.ORDERED)).willReturn(lista);
    }

    @Test
    public void findOne_EverythingOk_ReturnOrderedMeal() {
        OrderedMeal pronadjena = this.orderedMealService.findOne(ID_1);
        verify(this.orderedMealRepository).findOneById(ID_1);
        assertTrue(USER_ID == pronadjena.getCook().getId());
    }

    @Test
    public void findByStatus_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjena = this.orderedMealService.findByStatus(OrderedItemStatus.ORDERED);
        verify(this.orderedMealRepository).findByStatus(OrderedItemStatus.ORDERED);
        assertEquals(1, pronadjena.size());
    }

    @Test
    public void findByCook_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjena = this.orderedMealService.findByCook(USER_ID);
        verify(this.orderedMealRepository).findByCookId(USER_ID);
        assertEquals(1, pronadjena.size());
    }

    @Test
    public void findByCookIdAndStatus_EverythingOk_ReturnListOrderedMeal() {
        List<OrderedMeal> pronadjena = this.orderedMealService.findByCookIdAndStatus(USER_ID, OrderedItemStatus.ORDERED);
        verify(this.orderedMealRepository).findByCookIdAndStatus(USER_ID, OrderedItemStatus.ORDERED);
        assertEquals(1, pronadjena.size());
    }
}
