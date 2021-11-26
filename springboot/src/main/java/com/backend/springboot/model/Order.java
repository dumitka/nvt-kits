package com.backend.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

	//todo status porudzbine?
}
