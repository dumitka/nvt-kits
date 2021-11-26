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
@Table(name = "ordered_drink")
public class OrderedDrink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "amount")
	private Integer amount;

	@ManyToOne
	@JoinColumn(name = "drink_id", nullable = false)
	private Drink drink;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User bartender;

	@Column(name = "status")
	private OrderedItemStatus status;
}
