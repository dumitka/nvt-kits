package com.backend.springboot.dto;

import com.backend.springboot.model.DrinkCard;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkPriceDTO {
    private Integer id;
    private DrinkDTO drinkDTO;
    private Float price;
    private int lastDrinkCardId;                // da nemamo kruznju vezu
    private DrinkCardDTO lastDrinkCardDTO;      // opciono
}
