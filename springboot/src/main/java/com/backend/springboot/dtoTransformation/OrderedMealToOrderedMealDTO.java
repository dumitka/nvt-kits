package com.backend.springboot.dtoTransformation;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.model.OrderedMeal;

@Component
public class OrderedMealToOrderedMealDTO implements Converter<OrderedMeal, OrderedMealDTO>{

	@Override
	public OrderedMealDTO convert(OrderedMeal meal) {
		OrderedMealDTO dto = OrderedMealDTO.builder()
										   .id(meal.getId())
										   .amount(meal.getAmount())
										   .mealId(meal.getMeal().getId())
										   .orderId(meal.getOrder().getId())
										   .cookId(meal.getCook().getId())
										   .status(meal.getStatus())
										   .build();
		return dto;
	}
	
	public Set<OrderedMealDTO> convertSet(Set<OrderedMeal> meals) {
		return meals.stream().map(m -> convert(m)).collect(Collectors.toSet());
    }
}
