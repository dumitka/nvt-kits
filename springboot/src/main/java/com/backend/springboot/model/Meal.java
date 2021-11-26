package com.backend.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;

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
@Entity
@Table(name = "meal")
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private MealType type;

	@Column(name = "description")
	private String description;

	@Column(name = "meal_difficulty")
	private MealDifficulty mealDifficulty;

	@Column(name = "time_preparation")
	private Integer timePreparation;

	@Column(name = "amount_number", nullable = false)
	private Integer amountNumber;

	@Column(name = "amount_unit", nullable = false)
	private String amountUnit;

	@Column(name = "image")
	private String image;
	
	@Column(name = "deleted")
	private Boolean deleted;
}
