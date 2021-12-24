package com.backend.springboot.dtoTransformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;


import com.backend.springboot.dto.MealDTO;
import com.backend.springboot.dto.MealWithPriceDTO;
import com.backend.springboot.model.MealPrice;

public class MealPriceToMealWithPriceDTO implements Converter<MealPrice, MealWithPriceDTO>{
	private MealToMealDTO mealToMealDTO;
	
	@Override
	public MealWithPriceDTO convert(MealPrice source) {
		MealWithPriceDTO returnValue = new MealWithPriceDTO();
		returnValue.setId(source.getId());
		MealDTO mealDTO = mealToMealDTO.convert(source.getMeal());
		returnValue.setMealDTO(mealDTO);
		returnValue.setPrice(source.getPriceAmount());
		return returnValue;
	}
	
	public List<MealWithPriceDTO> convertList(List<MealPrice> meals) {
        List<MealWithPriceDTO> returnValue = new ArrayList<MealWithPriceDTO>();
        for (MealPrice m : meals) {
        	returnValue.add(convert(m));
        }
        return returnValue;
    }

}
