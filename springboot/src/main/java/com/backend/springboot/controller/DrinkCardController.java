package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkCardDTO;
import com.backend.springboot.dto.DrinkPriceDTO;
import com.backend.springboot.dtoTransformation.DrinkCardToDrinkCardDTO;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.DrinkPriceService;
import com.backend.springboot.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/drinkCards")
public class DrinkCardController {

    private final DrinkCardService drinkCardService;
    private final DrinkCardToDrinkCardDTO drinkCardToDrinkCardDTO;
    private final DrinkPriceService drinkPriceService;
    private final RestaurantService restaurantService;

    @Autowired
    public DrinkCardController(DrinkCardService drinkCardService, DrinkCardToDrinkCardDTO drinkCardToDrinkCardDTO,
                               DrinkPriceService drinkPriceService, RestaurantService restaurantService) {
        this.drinkCardService = drinkCardService;
        this.drinkCardToDrinkCardDTO = drinkCardToDrinkCardDTO;
        this.drinkPriceService = drinkPriceService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('SERVER')")
    public ResponseEntity<DrinkCardDTO> gettingDrinkCard() {
       DrinkCard najnovijaKP = this.drinkCardService.findLatest();
       DrinkCardDTO dto = this.drinkCardToDrinkCardDTO.convert(najnovijaKP);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/addDrinkCard")
    @PreAuthorize("hasRole('SERVER')")
    public ResponseEntity<DrinkCardDTO> addingDrinkCard(@RequestBody DrinkCardDTO drinkCardDTO) {
        Set<DrinkPrice> listaCP = new HashSet<>();
        for (DrinkPriceDTO dtoCP : drinkCardDTO.getDrinkPriceDTOs())
            listaCP.add(this.drinkPriceService.findByDrinkAndPrice(dtoCP.getId(), dtoCP.getPrice()));
        DrinkCard nova = DrinkCard.builder().drinkPrices(listaCP).dateOfValidation(LocalDateTime.now())
                .restaurant(restaurantService.findOne(drinkCardDTO.getRestaurantId())).build();
        DrinkCardDTO dto = this.drinkCardToDrinkCardDTO.convert(nova);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }
}
