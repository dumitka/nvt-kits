package com.backend.springboot.dto;

import com.backend.springboot.enums.ReportType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CreateReportDto {

    private ReportType reportType;
    private LocalDateTime date;
    private Float type;
}
