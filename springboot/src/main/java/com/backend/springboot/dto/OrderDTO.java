package com.backend.springboot.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Integer id;
	private Set<OrderedMealDTO> orderedMeals;
	private Set<OrderedDrinkDTO> orderedDrinks;
	private Integer waiterId;
	private Set<Integer> notificationIds;
	private Integer deskId;
}
