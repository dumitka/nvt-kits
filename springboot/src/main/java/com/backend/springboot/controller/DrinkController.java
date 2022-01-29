package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkDTO;
import com.backend.springboot.dtoTransformation.DrinkToDrinkDTO;
import com.backend.springboot.enums.DrinkType;
import com.backend.springboot.model.Drink;
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final DrinkCardService drinkCardService;
    private final DrinkToDrinkDTO drinkToDrinkDTO;

    @Autowired
    public DrinkController(DrinkService drinkService, DrinkCardService drinkCardService, DrinkToDrinkDTO drinkToDrinkDTO) {
        this.drinkService = drinkService;
        this.drinkCardService = drinkCardService;
        this.drinkToDrinkDTO = drinkToDrinkDTO;
    }

    @PostMapping("/addDrink")
    @PreAuthorize("hasRole('ROLE_SERVER')")
    public ResponseEntity<DrinkDTO> addingNewDrink(@RequestBody DrinkDTO dto) {
        if (!this.drinkService.freeNameAndAmount(dto.getName(), dto.getAmountUnit(), dto.getAmountNumber()))
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        Drink novoPice = Drink.builder()
                .name(dto.getName()).amountNumber(dto.getAmountNumber()).amountUnit(dto.getAmountUnit())
                .available(true).description(dto.getDescription()).image(dto.getImage()).type(dto.getType())
                .build();
        this.drinkService.save(novoPice);
        dto.setId(novoPice.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/editDrink")
    @PreAuthorize("hasRole('ROLE_SERVER')")
    public ResponseEntity<DrinkDTO> editingDrink(@RequestBody DrinkDTO dto) {
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
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/deleteDrink")
    @PreAuthorize("hasRole('ROLE_SERVER')")
    public ResponseEntity<DrinkDTO> deletingDrink(@RequestBody DrinkDTO dto) {
        Drink pice = this.drinkService.findOne(dto.getId());        // pice =! null
        pice.setAvailable(false);
        this.drinkService.save(pice);
        this.drinkCardService.removeDrink(pice);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/searchDrinks/{input}")
    @PreAuthorize("hasRole('ROLE_SERVER')")
    public ResponseEntity<List<DrinkDTO>> searchingDrinks(@PathVariable String input) {
        List<Drink> pronadjenaPica = this.drinkService.findByName(input);
        List<DrinkDTO> konvertovanaLista = this.drinkToDrinkDTO.convertList(pronadjenaPica);
        return new ResponseEntity<>(konvertovanaLista, HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_SERVER', 'ROLE_WAITER')")
    public ResponseEntity<List<DrinkDTO>> gettingDrinks() {
        List<Drink> pronadjenaPica = this.drinkService.findAllAvailable();
        List<DrinkDTO> konvertovanaLista = this.drinkToDrinkDTO.convertList(pronadjenaPica);
        return new ResponseEntity<>(konvertovanaLista, HttpStatus.OK);
    }

}
