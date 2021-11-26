package com.backend.springboot.controller;

import com.backend.springboot.dto.CreateReportDto;
import com.backend.springboot.dto.ReportDto;
import com.backend.springboot.dtoTransformation.ReportMapper;
import com.backend.springboot.model.Report;
import com.backend.springboot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportMapper reportMapper;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_DIRECTOR', 'ROLE_MANAGER')")
    public ResponseEntity<List<ReportDto>> getAll() {
        List<Report> reports = reportService.getAll();
        List<ReportDto> dtos = reports.stream().map(report ->
                reportMapper.convertreportToReportDto(report)).collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<ReportDto> createNew(@RequestBody CreateReportDto dto) {
        Report report = reportService.createNewReport(dto);

        return new ResponseEntity<>(reportMapper.convertreportToReportDto(report), HttpStatus.OK);
    }
}
