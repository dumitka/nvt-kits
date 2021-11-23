package com.backend.springboot.model;


import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "restaurant")
	private Set<Menu> menus;

	@OneToMany(mappedBy = "restaurant")
	private Set<Desk> desks;

	@OneToMany(mappedBy = "restaurant")
	private Set<DrinkCard> drinkCards;

	@OneToMany(mappedBy = "restaurant")
	private Set<Salary> salaries;

	@OneToMany(mappedBy = "restaurant")
	private Set<Report> reports;
}
