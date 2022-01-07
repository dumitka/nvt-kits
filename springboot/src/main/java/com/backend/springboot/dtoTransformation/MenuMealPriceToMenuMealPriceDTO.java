package com.backend.springboot.dtoTransformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.dto.MenuMealPriceDTO;

public class MenuMealPriceToMenuMealPriceDTO implements Converter<MenuMealPrice, MenuMealPriceDTO>{

	private MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO;
	
	@Autowired
	public MenuMealPriceToMenuMealPriceDTO(MealPriceToMealWithPriceDTO mealPriceToMealWithPriceDTO) {
		this.mealPriceToMealWithPriceDTO = mealPriceToMealWithPriceDTO;
	}
	
	@Override
	public MenuMealPriceDTO convert(MenuMealPrice source) {
		MenuMealPriceDTO returnValue = new MenuMealPriceDTO();
		returnValue.setId(source.getId());
		MealWithPriceDTO mealPrice = mealPriceToMealWithPriceDTO.convert(source.getMealPrice());
		returnValue.setMealWithPriceDTO(mealPrice);
		return returnValue;
	}
	
	public List<MenuMealPriceDTO> convert(List<MenuMealPrice> sources) {
        List<MenuMealPriceDTO> returnValue = new ArrayList<>();
        for (MenuMealPrice mmp : sources) returnValue.add(convert(mmp));
        return returnValue;
    }

}
