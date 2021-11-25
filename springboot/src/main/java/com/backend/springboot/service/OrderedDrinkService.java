package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.repository.OrderedDrinkRepository;

public class OrderedDrinkService {
	@Autowired
	private OrderedDrinkRepository orderedDrinkRepository;
	
	public OrderedDrink findOne(Integer id) {
		return orderedDrinkRepository.findOneById(id);
	}
	
	public OrderedDrink save(OrderedDrink orderedDrink) {
		return orderedDrinkRepository.save(orderedDrink);
	}
}
