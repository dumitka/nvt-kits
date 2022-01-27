package com.backend.springboot.model;

import java.util.Set;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drink_price")
public class DrinkPrice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Drink drink;

    @Column(name = "price", nullable = false)
    private Float price;

    @ManyToMany(mappedBy = "drinkPrices")
    private Set<DrinkCard> drinkCards;
}
