package com.backend.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.springboot.enums.DrinkType;

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
@Table(name = "drink")
public class Drink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private DrinkType type;

	@Column(name = "description")
	private String description;

	@Column(name = "amount_number", nullable = false)
	private double amountNumber;

	@Column(name = "amount_unit", nullable = false)
	private String amountUnit;

	@Column(name = "available", nullable = false)
	private boolean available;				// logicko brisanje zbog starih narudzbina

	@Column(name = "image")
	private String image;

}
