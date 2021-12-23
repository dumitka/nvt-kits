package com.backend.springboot.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuMealPriceDTO {
	private Integer id;
	private MealWithPriceDTO mealWithPriceDTO;
}
