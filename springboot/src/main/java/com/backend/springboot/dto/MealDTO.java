package com.backend.springboot.dto;
import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;

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
public class MealDTO {
	
	private Integer id;
	private String name;
	private MealType type;
	private String description;
	private MealDifficulty mealDifficulty;
	private Integer timePreparation;
	private Integer amountNumber;
	private String amountUnit;
	private String image;
	private Boolean deleted;
}
