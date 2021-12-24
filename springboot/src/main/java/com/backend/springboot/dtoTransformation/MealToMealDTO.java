package com.backend.springboot.dtoTransformation;

import org.springframework.core.convert.converter.Converter;

import com.backend.springboot.dto.MealDTO;
import com.backend.springboot.model.Meal;

public class MealToMealDTO implements Converter<Meal, MealDTO> {
	
	@Override
	public MealDTO convert(Meal source) {
		MealDTO returnValue = new MealDTO();
		returnValue.setId(source.getId());
		returnValue.setAmountNumber(source.getAmountNumber());
		returnValue.setAmountUnit(source.getAmountUnit());
		returnValue.setDeleted(source.getDeleted());
		returnValue.setDescription(source.getDescription());
		returnValue.setImage(source.getImage());
		returnValue.setMealDifficulty(source.getMealDifficulty());
		returnValue.setName(source.getName());
		returnValue.setTimePreparation(source.getTimePreparation());
		returnValue.setType(source.getType());
		return returnValue;
	}

}
