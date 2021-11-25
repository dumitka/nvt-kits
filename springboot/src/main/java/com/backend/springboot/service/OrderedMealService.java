package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.repository.OrderedMealRepository;

public class OrderedMealService {
	@Autowired
	private OrderedMealRepository orderedMealRepository;
	
	public OrderedMeal findOne(Integer id) {
		return orderedMealRepository.findOneById(id);
	}
	
	public OrderedMeal save(OrderedMeal orderedMeal) {
		return orderedMealRepository.save(orderedMeal);
	}
}
