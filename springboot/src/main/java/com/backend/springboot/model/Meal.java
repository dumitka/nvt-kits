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
@Table(name = "meals")
public class Meal {
	
	public Meal(String name, MealType type, String description, MealDifficulty mealDifficulty, int timePreparation, int amountNumber, String amountUnit, String image, boolean deleted) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.mealDifficulty = mealDifficulty;
		this.timePreparation = timePreparation;
		this.amountNumber = amountNumber;
		this.amountUnit = amountUnit;
		this.image = image;
		this.deleted = deleted;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private MealType type;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "meal_difficulty", nullable = true)
	private MealDifficulty mealDifficulty;

	@Column(name = "time_preparation", nullable = true)
	private Integer timePreparation;

	@Column(name = "amount_number", nullable = true)
	private Integer amountNumber;

	@Column(name = "amount_unit", nullable = true)
	private String amountUnit;

	@Column(name = "image", nullable = true)
	private String image;
	
	@Column(name = "deleted", nullable = false)
	private Boolean deleted;
	
}
