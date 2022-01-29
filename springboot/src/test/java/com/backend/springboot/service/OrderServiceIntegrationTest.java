package com.backend.springboot.service;

import com.backend.springboot.model.Desk;
import com.backend.springboot.model.Order;
import com.backend.springboot.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.OrderConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findOne_EverythingOk_ReturnOrder() {
        Order pronadjena = this.orderService.findOne(ID_1);
        assertEquals(DESK_ID_1, pronadjena.getDesk().getId());
    }

    @Test
    public void findOrderForDesk_EverythingOk_ReturnOrder() {
        Order pronadjena = this.orderService.findOrderForDesk(DESK_ID_1);
        assertTrue(ID_1 == pronadjena.getId());
    }

    @Test
    public void save_EverythingOk_ReturnOrder() {
        Order nova = Order.builder().desk(Desk.builder().id(DESK_ID_1).build()).isDeleted(false).build();
        Order pronadjena =  this.orderService.save(nova);
        assertTrue(ID_NOV == pronadjena.getId());
        this.orderRepository.delete(pronadjena);
    }

}
