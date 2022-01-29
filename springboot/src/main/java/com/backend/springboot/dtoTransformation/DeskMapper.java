package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.DeskDTO;
import com.backend.springboot.model.Desk;
import org.springframework.stereotype.Component;

@Component
public class DeskMapper {

    public DeskDTO toDeskDTO(Desk desk) {
        return DeskDTO.builder()
                .id(desk.getId())
                .deskStatus(desk.getDeskStatus())
                .x(desk.getX())
                .y(desk.getY())
                .height(desk.getHeight())
                .width(desk.getWidth())
                .reserved(desk.isReserved())
                .tableNum(desk.getTableNum())
                .build();

    }
}
