package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkDTO;
import com.backend.springboot.model.Drink;
import com.backend.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/drinks/")
public class DrinkController {

    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping("/addDrink")
    @PreAuthorize("hasRole('SERVER')")
    public ResponseEntity<DrinkDTO> addingNewDrink(DrinkDTO dto) {
        if (this.drinkService.freeNameAndAmount(dto.getName(), dto.getAmountUnit(), dto.getAmountNumber()))
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        Drink novoPice = Drink.builder()
                .name(dto.getName()).amountNumber(dto.getAmountNumber()).amountUnit(dto.getAmountUnit())
                .available(true).description(dto.getDescription()).image(dto.getImage()).type(dto.getType())
                .build();
        this.drinkService.save(novoPice);
        dto.setId(novoPice.getId());
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }
}
