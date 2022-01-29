package com.backend.springboot.service;

import com.backend.springboot.model.Desk;
import com.backend.springboot.model.Order;
import com.backend.springboot.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.OrderConstants.DESK_ID_1;
import static com.backend.springboot.constants.OrderConstants.ID_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceUnitTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Before
    public void setUpp() {
        Order sacuvanaPorudzbina = Order.builder().id(ID_1).desk(Desk.builder().id(DESK_ID_1).build()).build();

        given(this.orderRepository.findOneByDeskIdAndIsDeletedFalse(DESK_ID_1)).willReturn(sacuvanaPorudzbina);
        given(this.orderRepository.findOneByIdAndIsDeletedFalse(ID_1)).willReturn(sacuvanaPorudzbina);

    }

    @Test
    public void findOne_EverythingOk_ReturnOrder() {
        Order pronadjena = this.orderService.findOne(ID_1);
        verify(this.orderRepository).findOneByIdAndIsDeletedFalse(ID_1);
        assertEquals(DESK_ID_1, pronadjena.getDesk().getId());
    }

    @Test
    public void findOrderForDesk_EverythingOk_ReturnOrder() {
        Order pronadjena = this.orderService.findOrderForDesk(DESK_ID_1);
        verify(this.orderRepository).findOneByDeskIdAndIsDeletedFalse(DESK_ID_1);
        assertTrue(ID_1 == pronadjena.getId());
    }

    /*

	public Order save(Order order) {
		return orderRepository.save(order);
	}*/
}
