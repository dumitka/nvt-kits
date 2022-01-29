package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.repository.OrderedDrinkRepository;

@Service
public class OrderedDrinkService {
	@Autowired
	private OrderedDrinkRepository orderedDrinkRepository;

	public OrderedDrink findOne(Integer id) {
		return orderedDrinkRepository.findOneById(id);
	}
	
	public List<OrderedDrink> findByStatus(OrderedItemStatus status) {
		return orderedDrinkRepository.findByStatus(status);
	}
	
	public List<OrderedDrink> findByBartender(Integer userId) {
		return orderedDrinkRepository.findByBartenderId(userId);
	}

	public OrderedDrink save(OrderedDrink orderedDrink) {
		return orderedDrinkRepository.save(orderedDrink);
	}

	public List<OrderedDrink> findByBartenderIdAndStatus(Integer userId, OrderedItemStatus status) {
		return orderedDrinkRepository.findByBartenderIdAndStatus(userId, status);
	}
}
