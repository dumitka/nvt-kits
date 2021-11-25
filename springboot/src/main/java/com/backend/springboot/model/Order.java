package com.backend.springboot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "order")
	private Set<OrderedMeal> orderedMeals;

	@OneToMany(mappedBy = "order")
	private Set<OrderedDrink> orderedDrinks;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User waiter;

	@OneToMany(mappedBy = "order")
	private Set<Notification> notifications;

	@ManyToOne
	@JoinColumn(name = "desk_id")
	private Desk desk;
	
	@Column(name = "deleted")
	private Boolean isDeleted;
}
