package com.backend.springboot.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkCardDTO {

    private Integer id;
    private LocalDateTime dateOfValidation;
    private Set<DrinkPriceDTO> drinkPriceDTOs;
    private int restaurantId;                   // da ne bude rekurzije
}
