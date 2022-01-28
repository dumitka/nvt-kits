package com.backend.springboot.dtoTransformation;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.springboot.dto.DeskDTO;
import com.backend.springboot.model.Desk;

@Component
public class DeskToDeskDTO implements Converter<Desk, DeskDTO> {
	@Override
	public DeskDTO convert(Desk desk) {
		DeskDTO dto = DeskDTO.builder()
				.id(desk.getId())
				.deskStatus(desk.getDeskStatus())
				.build();
		return dto;
	}
}
