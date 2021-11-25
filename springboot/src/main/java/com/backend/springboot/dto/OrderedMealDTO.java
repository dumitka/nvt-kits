package com.backend.springboot.dto;

import com.backend.springboot.enums.OrderedItemStatus;

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
public class OrderedMealDTO {
	private Integer id;
	private Integer amount;
	private Integer mealId;
	private Integer orderId;
	private Integer cookId;
	private OrderedItemStatus status;
}
