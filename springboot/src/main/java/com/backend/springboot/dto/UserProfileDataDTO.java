package com.backend.springboot.dto;

import com.backend.springboot.enums.DrinkType;

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
@AllArgsConstructor
@Builder
public class UserProfileDataDTO {
	private Integer id;
	private String name;
	private String lastName;
}
