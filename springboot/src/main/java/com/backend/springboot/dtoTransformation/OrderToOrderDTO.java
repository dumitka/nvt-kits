package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.OrderDTO;
import com.backend.springboot.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderToOrderDTO implements Converter<Order, OrderDTO> {
	@Autowired
	private OrderedMealToOrderedMealDTO mealToMealDTO;

	@Autowired
	private OrderedDrinkToOrderedDrinkDTO drinkToDrinkDTO;

	@Override
	public OrderDTO convert(Order order) {
		Set<Integer> notificationIds = order.getNotifications().stream().map(n -> n.getId()).collect(Collectors.toSet());

		OrderDTO dto = OrderDTO.builder()
				.id(order.getId())
				.orderedMeals(mealToMealDTO.convertSet(order.getOrderedMeals()))
				.orderedDrinks(drinkToDrinkDTO.convertSet(order.getOrderedDrinks()))
				.notificationIds(notificationIds)
				.deskId(order.getDesk().getId())
				.build();
		return dto;
	}

	public Set<OrderDTO> convertSet(Set<Order> orders) {
		return orders.stream().map(o -> convert(o)).collect(Collectors.toSet());
	}
}
