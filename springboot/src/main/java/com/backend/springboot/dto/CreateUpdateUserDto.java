package com.backend.springboot.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateUserDto {

    private String username;
    private String name;
    private String lastName;
    private String password;
    private String roleName;
    private float salary;

}
