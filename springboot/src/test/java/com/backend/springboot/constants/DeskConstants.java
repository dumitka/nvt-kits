package com.backend.springboot.constants;

import com.backend.springboot.enums.DeskStatus;
import com.backend.springboot.model.Desk;

public class DeskConstants {

    public static final Desk DESK = Desk.builder()
            .deskStatus(DeskStatus.NOT_ORDERED)
            .tip(0.0F)
            .x(0.1F)
            .y(0.1F)
            .height(0.2F)
            .width(0.2F)
            .reserved(false)
            .deleted(false)
            .build();

    public static final Desk DESK_DELETED = Desk.builder()
            .deskStatus(DeskStatus.NOT_ORDERED)
            .tip(0.0F)
            .x(0.1F)
            .y(0.1F)
            .height(0.2F)
            .width(0.2F)
            .reserved(false)
            .deleted(true)
            .build();
}
