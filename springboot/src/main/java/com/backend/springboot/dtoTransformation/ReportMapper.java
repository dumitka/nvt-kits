package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.ReportDto;
import com.backend.springboot.model.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportDto convertreportToReportDto(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .billExpense(report.getBillExpense())
                .salaryExpense(report.getSalaryExpense())
                .purchaseExpense(report.getPurchaseExpense())
                .incomeOfDrinkSale(report.getIncomeOfDrinkSale())
                .incomeOfFoodSale(report.getIncomeOfFoodSale())
                .date(report.getDate())
                .reportType(report.getReportType())
                .type(report.getTip())
                .build();
    }
}
