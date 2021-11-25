package com.backend.springboot.dtoTransformation;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.model.OrderedDrink;

@Component
public class OrderedDrinkToOrderedDrinkDTO implements Converter<OrderedDrink, OrderedDrinkDTO>{

	@Override
	public OrderedDrinkDTO convert(OrderedDrink drink) {
		OrderedDrinkDTO dto = OrderedDrinkDTO.builder()
											 .id(drink.getId())
											 .amount(drink.getAmount())
											 .drinkId(drink.getDrink().getId())
											 .orderId(drink.getOrder().getId())
											 .bartenderId(drink.getBartender().getId())
											 .status(drink.getStatus())
											 .build();
		return dto;
	}
	
	public Set<OrderedDrinkDTO> convertSet(Set<OrderedDrink> drinks) {
		return drinks.stream().map(d -> convert(d)).collect(Collectors.toSet());
    }
}
