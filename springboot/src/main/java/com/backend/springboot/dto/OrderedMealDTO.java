package com.backend.springboot.dto;

import com.backend.springboot.enums.OrderedItemStatus;
import lombok.*;

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
