package com.backend.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class SalaryDto {

    private Integer id;
    private float amount;
    private Integer userId;
    private LocalDateTime localDateTime;
}
