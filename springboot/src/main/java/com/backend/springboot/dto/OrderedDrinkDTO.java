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
public class OrderedDrinkDTO {
	private Integer id;
	private Integer amount;
	private Integer drinkId;
	private Integer orderId;
	private Integer bartenderId;
	private OrderedItemStatus status;
}
