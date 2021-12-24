package com.backend.springboot.controller;

import com.backend.springboot.dto.DrinkCardDTO;
import com.backend.springboot.dto.DrinkPriceDTO;
import com.backend.springboot.dtoTransformation.DrinkCardToDrinkCardDTO;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.DrinkPriceService;
import com.backend.springboot.service.DrinkService;
import com.backend.springboot.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/drinkCards")
public class DrinkCardController {

    private final DrinkCardService drinkCardService;
    private final DrinkCardToDrinkCardDTO drinkCardToDrinkCardDTO;
    private final DrinkPriceService drinkPriceService;
    private final RestaurantService restaurantService;
    private final DrinkService drinkService;

    @Autowired
    public DrinkCardController(DrinkCardService drinkCardService, DrinkCardToDrinkCardDTO drinkCardToDrinkCardDTO,
                               DrinkPriceService drinkPriceService, RestaurantService restaurantService,
                               DrinkService drinkService) {
        this.drinkCardService = drinkCardService;
        this.drinkCardToDrinkCardDTO = drinkCardToDrinkCardDTO;
        this.drinkPriceService = drinkPriceService;
        this.restaurantService = restaurantService;
        this.drinkService = drinkService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_SERVER')")
    public ResponseEntity<DrinkCardDTO> gettingDrinkCard() {
       DrinkCard najnovijaKP = this.drinkCardService.findLatest();
       DrinkCardDTO dto = this.drinkCardToDrinkCardDTO.convert(najnovijaKP);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // dodavanje ili izmena, svede se na isto tj pravljenje nove karte pica
    @PostMapping("/newDrinkCard")
    @PreAuthorize("hasRole('ROLE_SERVER')")
    public ResponseEntity<DrinkCardDTO> newDrinkCard(@RequestBody DrinkCardDTO drinkCardDTO) {
        Set<DrinkPrice> listaCP = new HashSet<>();
        for (DrinkPriceDTO dtoCP : drinkCardDTO.getDrinkPriceDTOs()) {
            DrinkPrice cena = this.drinkPriceService.findByDrinkAndPrice(dtoCP.getDrinkDTO().getId(), dtoCP.getPrice());
            if (cena == null) {
                cena = DrinkPrice.builder().drink(this.drinkService.findOne(dtoCP.getDrinkDTO().getId())).
                        price(dtoCP.getPrice()).drinkCards(new HashSet<>()).build();
            }
            listaCP.add(cena);
        }
        DrinkCard nova = DrinkCard.builder().drinkPrices(listaCP).dateOfValidation(LocalDateTime.now())
                .restaurant(restaurantService.findOne(drinkCardDTO.getRestaurantId())).build();
        listaCP.stream().forEach(cp -> { cp.getDrinkCards().add(nova); this.drinkPriceService.save(cp); });
        this.drinkCardService.save(nova);
        DrinkCardDTO dto = this.drinkCardToDrinkCardDTO.convert(nova);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
