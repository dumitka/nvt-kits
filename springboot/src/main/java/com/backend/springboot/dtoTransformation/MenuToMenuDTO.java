package com.backend.springboot.dtoTransformation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


import com.backend.springboot.dto.MenuDTO;
import com.backend.springboot.dto.MenuMealPriceDTO;
import com.backend.springboot.model.Menu;


public class MenuToMenuDTO implements Converter<Menu, MenuDTO>{

	private MenuMealPriceToMenuMealPriceDTO menuMealPriceToMenuMealPriceDTO;
	
	@Autowired
    public MenuToMenuDTO(MenuMealPriceToMenuMealPriceDTO menuMealPriceToMenuMealPriceDTO) {
        this.menuMealPriceToMenuMealPriceDTO = menuMealPriceToMenuMealPriceDTO;
    }
	
	@Override
	public MenuDTO convert(Menu source) {
		MenuDTO returnValue = new MenuDTO();
		returnValue.setId(source.getId());
		returnValue.setDateOfValidation(source.getDateOfValidation());
		if(!source.getMenuMealPrices().isEmpty()){
			Set<MenuMealPriceDTO> set = Set.copyOf(this.menuMealPriceToMenuMealPriceDTO.convert(source.getMenuMealPrices().stream().toList()));
			returnValue.setMenuMealPriceDTO(set);
		}
		return returnValue;
	}

}
