package com.backend.springboot.service;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.repository.OrderedMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedMealService {
	@Autowired
	private OrderedMealRepository orderedMealRepository;

	public OrderedMeal findOne(Integer id) {
		return orderedMealRepository.findOneById(id);
	}
	
	public List<OrderedMeal> findByStatus(OrderedItemStatus status) {
		return orderedMealRepository.findByStatus(status);
	}
	
	public List<OrderedMeal> findByUser(Integer userId) {
		return orderedMealRepository.findByUserId(userId);
	}

	public OrderedMeal save(OrderedMeal orderedMeal) {
		return orderedMealRepository.save(orderedMeal);
	}
}
