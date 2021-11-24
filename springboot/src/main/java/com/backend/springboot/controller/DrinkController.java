package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkDTO;
import com.backend.springboot.model.Drink;
import com.backend.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/editDrink")
    @PreAuthorize("hasRole('SERVER')")
    public ResponseEntity<DrinkDTO> editingDrink(DrinkDTO dto) {
        Drink pice = this.drinkService.findOne(dto.getId());        // pice =! null
        if (!this.drinkService.editableDrink(dto.getId(), dto.getName(), dto.getAmountUnit(), dto.getAmountNumber()))
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        pice.setAmountNumber(dto.getAmountNumber());
        pice.setAmountUnit(dto.getAmountUnit());
        pice.setAvailable(dto.isAvailable());
        pice.setDescription(dto.getDescription());
        pice.setImage(dto.getImage());
        pice.setName(dto.getName());
        pice.setType(dto.getType());
        this.drinkService.save(pice);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

}
