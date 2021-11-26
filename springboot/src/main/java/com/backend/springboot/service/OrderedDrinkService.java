package com.backend.springboot.service;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.repository.OrderedDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedDrinkService {
	@Autowired
	private OrderedDrinkRepository orderedDrinkRepository;

	public List<OrderedDrink> findByStatus(OrderedItemStatus status) {
		return orderedDrinkRepository.findByStatus(status);
	}

	public OrderedDrink findOne(Integer id) {
		return orderedDrinkRepository.findOneById(id);
	}

	public OrderedDrink save(OrderedDrink orderedDrink) {
		return orderedDrinkRepository.save(orderedDrink);
	}
}
