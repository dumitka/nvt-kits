package com.backend.springboot.dto;

import com.backend.springboot.enums.DeskStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeskDTO {

    private int id;
    private DeskStatus deskStatus;

    private float x;
    private float y;
    private float height;
    private float width;

    private boolean reserved;
    private int tableNum;
    private boolean deleted;
}
