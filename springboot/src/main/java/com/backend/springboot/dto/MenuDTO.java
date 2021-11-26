package com.backend.springboot.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.backend.springboot.enums.DrinkType;
import com.backend.springboot.model.MealPrice;

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
public class MenuDTO {
	Integer id;
	LocalDateTime dateOfValidation;
	private Set<MealPrice> mealPrices;
}
