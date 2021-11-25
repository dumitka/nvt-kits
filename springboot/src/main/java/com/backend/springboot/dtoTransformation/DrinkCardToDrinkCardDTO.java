package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.DrinkCardDTO;
import com.backend.springboot.dto.DrinkPriceDTO;
import com.backend.springboot.model.DrinkCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DrinkCardToDrinkCardDTO  implements Converter<DrinkCard, DrinkCardDTO> {

    private DrinkPriceToDrinkPriceDTO drinkPriceToDrinkPriceDTO;

    @Autowired
    public DrinkCardToDrinkCardDTO(DrinkPriceToDrinkPriceDTO drinkPriceToDrinkPriceDTO) {
        this.drinkPriceToDrinkPriceDTO = drinkPriceToDrinkPriceDTO;
    }

    @Override
    public DrinkCardDTO convert(DrinkCard source) {
        DrinkCardDTO povratnaKP = new DrinkCardDTO();
        povratnaKP.setId(source.getId());
        povratnaKP.setRestaurantId(source.getRestaurant().getId());
        Set<DrinkPriceDTO> skup =
                Set.copyOf(this.drinkPriceToDrinkPriceDTO.convert(source.getDrinkPrices().stream().toList()));
        povratnaKP.setDrinkPriceDTOs(skup);
        povratnaKP.setDateOfValidation(source.getDateOfValidation());
        return povratnaKP;
    }


    public List<DrinkCardDTO> convert(List<DrinkCard> sources) {
        List<DrinkCardDTO> povratnaListaKP = new ArrayList<>();
        for (DrinkCard dk : sources) povratnaListaKP.add(convert(dk));
        return povratnaListaKP;
    }
}
