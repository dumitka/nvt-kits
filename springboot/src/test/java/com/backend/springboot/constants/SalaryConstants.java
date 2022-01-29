package com.backend.springboot.constants;

import com.backend.springboot.model.Salary;
import com.backend.springboot.model.User;

import java.time.LocalDateTime;

public class SalaryConstants {

    public static final User USER = User.builder().build();
    public static final float AMOUNT = 45000.0F;
    public static final Salary SALARY = Salary.builder()
            .user(USER)
            .amount(AMOUNT)
            .dateOfValidation(LocalDateTime.now())
            .build();
}
