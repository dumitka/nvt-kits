package com.backend.springboot.dto;

import lombok.*;

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
