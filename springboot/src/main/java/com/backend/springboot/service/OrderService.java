package com.backend.springboot.service;

import com.backend.springboot.model.Order;
import com.backend.springboot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public Order findOne(Integer id) {
		return orderRepository.findOneById(id);
	}
	
	public Order findOrderForDesk(Integer deskId) {
		return orderRepository.findOneByDeskId(deskId);
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}
}
