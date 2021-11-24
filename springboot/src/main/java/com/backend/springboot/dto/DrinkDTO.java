package com.backend.springboot.dto;

import com.backend.springboot.enums.DrinkType;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkDTO {
    private Integer id;
    private String name;
    private DrinkType type;
    private String description;
    private double amountNumber;
    private String amountUnit;
    private boolean available;
    private String image;
}
