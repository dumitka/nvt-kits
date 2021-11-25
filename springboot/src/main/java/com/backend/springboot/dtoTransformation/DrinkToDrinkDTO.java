package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.DrinkDTO;
import com.backend.springboot.model.Drink;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DrinkToDrinkDTO implements Converter<Drink, DrinkDTO> {

    @Override
    public DrinkDTO convert(Drink drink) {
        DrinkDTO povratna = new DrinkDTO();
        povratna.setId(drink.getId());
        povratna.setAmountNumber(drink.getAmountNumber());
        povratna.setAmountUnit(drink.getAmountUnit());
        povratna.setAvailable(drink.isAvailable());
        povratna.setType(drink.getType());
        povratna.setName(drink.getName());
        povratna.setDescription(drink.getDescription());
        return povratna;
    }

    public List<DrinkDTO> convertList(List<Drink> drinks) {
        List<DrinkDTO> povratna = new ArrayList<>();
        for (Drink d : drinks) povratna.add(convert(d));
        return povratna;
    }
}
