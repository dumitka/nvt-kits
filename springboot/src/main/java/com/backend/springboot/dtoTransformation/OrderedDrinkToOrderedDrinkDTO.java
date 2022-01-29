package com.backend.springboot.dtoTransformation;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.model.OrderedDrink;

@Component
public class OrderedDrinkToOrderedDrinkDTO implements Converter<OrderedDrink, OrderedDrinkDTO> {

	@Override
	public OrderedDrinkDTO convert(OrderedDrink drink) {
		Integer bartenderId = null;
		if (drink.getBartender() != null) {
			bartenderId = drink.getBartender().getId();
		}
		
		OrderedDrinkDTO dto = OrderedDrinkDTO.builder()
				.id(drink.getId())
				.amount(drink.getAmount())
				.drinkId(drink.getDrink().getId())
				.drinkName(drink.getDrink().getName())
				.orderId(drink.getOrder().getId())
				.bartenderId(bartenderId)
				.status(drink.getStatus())
				.build();
		return dto;
	}

	public Set<OrderedDrinkDTO> convertSet(Set<OrderedDrink> drinks) {
		if (drinks != null) {
			return drinks.stream().map(d -> convert(d)).collect(Collectors.toSet());
		}
		
		return null;
	}
}
