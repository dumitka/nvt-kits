package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.springboot.model.Order;
import com.backend.springboot.repository.OrderRepository;

public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public Order findOne(Integer id) {
		return orderRepository.findOneById(id);
	}
	
	public Order save(Order order) {
		return orderRepository.save(order);
	}
}
