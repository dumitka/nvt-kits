package com.backend.springboot.model;

import com.backend.springboot.enums.OrderedItemStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_meal")
public class OrderedMeal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "amount")
	private Integer amount;

	@ManyToOne
	@JoinColumn(name = "meal_id", nullable = false)
	private Meal meal;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User cook;

	@Column(name = "status")
	private OrderedItemStatus status;
}
