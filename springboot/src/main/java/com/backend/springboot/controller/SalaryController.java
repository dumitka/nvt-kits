package com.backend.springboot.controller;

import com.backend.springboot.dto.SalaryDto;
import com.backend.springboot.dtoTransformation.UserMapper;
import com.backend.springboot.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public ResponseEntity<List<SalaryDto>> getAll() {
        List<SalaryDto> salaries = salaryService.getAll().stream().map(
                salary -> userMapper.convertSalaryToSalaryDto(salary)).collect(Collectors.toList());

        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }
}
