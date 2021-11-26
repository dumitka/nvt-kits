package com.backend.springboot.dto;

import com.backend.springboot.enums.ReportType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ReportDto {

    private Integer id;
    private ReportType reportType;
    private LocalDateTime date;
    private Float type;
    private Float salaryExpense;
    private Float purchaseExpense;
    private Float billExpense;
    private Float incomeOfFoodSale;
    private Float incomeOfDrinkSale;

}
