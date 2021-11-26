package com.backend.springboot.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
	private Set<Report> reports;
}
