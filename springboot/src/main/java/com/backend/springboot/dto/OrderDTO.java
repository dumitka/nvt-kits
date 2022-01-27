package com.backend.springboot.dto;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Integer id;
	private Set<OrderedMealDTO> orderedMeals;
	private Set<OrderedDrinkDTO> orderedDrinks;
	private Set<Integer> notificationIds;
	private Integer deskId;
}
