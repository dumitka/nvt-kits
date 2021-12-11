package com.backend.springboot.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

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
@Table(name = "drink_card")
public class DrinkCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date_Of_Validation", nullable = false)
	private LocalDateTime dateOfValidation;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "drink_prices", joinColumns = @JoinColumn(name = "drink_card_id"), inverseJoinColumns = @JoinColumn(name = "drink_price_id"))
	private Set<DrinkPrice> drinkPrices;

	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
}
