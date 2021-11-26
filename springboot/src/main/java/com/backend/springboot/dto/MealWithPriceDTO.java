package com.backend.springboot.dto;

import com.backend.springboot.enums.DrinkType;
import com.backend.springboot.model.Meal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealWithPriceDTO {
	private Meal meal;
	private Float price;
}
