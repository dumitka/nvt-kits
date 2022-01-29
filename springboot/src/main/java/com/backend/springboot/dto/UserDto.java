package com.backend.springboot.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String username;
    private String lastName;
    private String roleName;
    private float salary;

}
