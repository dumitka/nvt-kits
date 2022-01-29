package com.backend.springboot.dto;

import com.backend.springboot.enums.OrderedItemStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedDrinkDTO {
	private Integer id;
	private Integer amount;
	private Integer drinkId;
	private String drinkName;
	private Integer orderId;
	private Integer bartenderId;
	private OrderedItemStatus status;
}
