package com.backend.springboot.controller;


import com.backend.springboot.dto.DeskDTO;
import com.backend.springboot.dtoTransformation.DeskMapper;
import com.backend.springboot.model.Desk;
import com.backend.springboot.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/desks")
public class DeskController {

    @Autowired
    private DeskService deskService;

    @Autowired
    private DeskMapper mapper;

    //add desk


    @GetMapping(value = "/")
//    @PreAuthorize("hasAnyRole('','','')") //svi
    public ResponseEntity<List<DeskDTO>> getDesks() {

        List<Desk> desks = deskService.findAll();
        List<DeskDTO> dtos = desks.stream()
                .map(mapper::toDeskDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //update desk

}
