package com.backend.springboot.repository;

import com.backend.springboot.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.OrderConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findOneByIdAndIsDeletedFalse_EverythingOk_ReturnOrder() {
        Order pronadjen = this.orderRepository.findOneByIdAndIsDeletedFalse(ID_1);
        assertEquals(DESK_ID_1, pronadjen.getDesk().getId());
    }

    @Test
    public void findOneByDeskIdAndIsDeletedFalse_EverythingOk_ReturnOrder() {
        Order pronadjen = this.orderRepository.findOneByDeskIdAndIsDeletedFalse(DESK_ID_2);
        assertTrue(ID_2 == pronadjen.getId());
    }
}
