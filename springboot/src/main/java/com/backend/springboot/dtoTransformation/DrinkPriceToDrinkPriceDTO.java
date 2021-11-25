package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.DrinkPriceDTO;
import com.backend.springboot.model.DrinkPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class DrinkPriceToDrinkPriceDTO implements Converter<DrinkPrice, DrinkPriceDTO> {

    private DrinkToDrinkDTO drinkToDrinkDTO;

    @Autowired
    public DrinkPriceToDrinkPriceDTO(DrinkToDrinkDTO drinkToDrinkDTO) {
        this.drinkToDrinkDTO = drinkToDrinkDTO;
    }

    @Override
    public DrinkPriceDTO convert(DrinkPrice source) {
        DrinkPriceDTO cenaPica = new DrinkPriceDTO();
        cenaPica.setDrinkDTO(this.drinkToDrinkDTO.convert(source.getDrink()));
        cenaPica.setId(source.getId());
        cenaPica.setPrice(source.getPrice());
        // ako nekome bude trebalo DrinkCardId moze ovde ubaciti
        // a ako treba DrinkCardDTO onda neka napravi novu funkciju
        return cenaPica;
    }

    public List<DrinkPriceDTO> convert(List<DrinkPrice> sources) {
        List<DrinkPriceDTO> povratnaListaCP = new ArrayList<>();
        for (DrinkPrice cp : sources) povratnaListaCP.add(convert(cp));
        return povratnaListaCP;
    }
}
