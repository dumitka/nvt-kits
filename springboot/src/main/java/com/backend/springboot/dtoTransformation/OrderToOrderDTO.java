package com.backend.springboot.dtoTransformation;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.springboot.dto.OrderDTO;
import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.model.OrderedMeal;

@Component
public class OrderToOrderDTO implements Converter<Order, OrderDTO> {
	@Autowired
	private OrderedMealToOrderedMealDTO mealToMealDTO;

	@Autowired
	private OrderedDrinkToOrderedDrinkDTO drinkToDrinkDTO;

	@Override
	public OrderDTO convert(Order order) {
		Set<Integer> notificationIds = order.getNotifications().stream().map(n -> n.getId()).collect(Collectors.toSet());

		Set<OrderedMeal> orderedMeals = order.getOrderedMeals();
		Set<OrderedDrink> orderedDrinks = order.getOrderedDrinks();
		Set<OrderedMealDTO> orderedMealsDTO = new HashSet<OrderedMealDTO>();
		Set<OrderedDrinkDTO> orderedDrinksDTO = new HashSet<OrderedDrinkDTO>();
		
		if (orderedMeals != null) {
			orderedMealsDTO = mealToMealDTO.convertSet(orderedMeals);
		}
		
		if (orderedDrinks != null) {
			orderedDrinksDTO = drinkToDrinkDTO.convertSet(orderedDrinks);
		}
		
		OrderDTO dto = OrderDTO.builder()
				.id(order.getId())
				.orderedMeals(orderedMealsDTO)
				.orderedDrinks(orderedDrinksDTO)
				.notificationIds(notificationIds)
				.deskId(order.getDesk().getId())
				.build();
		return dto;
	}

	public Set<OrderDTO> convertSet(Set<Order> orders) {
		return orders.stream().map(o -> convert(o)).collect(Collectors.toSet());
	}
}
