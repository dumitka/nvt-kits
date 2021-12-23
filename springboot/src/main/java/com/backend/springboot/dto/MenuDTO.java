package com.backend.springboot.dto;

import java.time.LocalDateTime;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {
	private Integer id;
	private LocalDateTime dateOfValidation;
	private Boolean current;
	private Set<MenuMealPriceDTO> menuMealPriceDTO;
}
