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

    private Integer id;
    private DeskStatus deskStatus;

    private int x;
    private int y;
    private float height;
    private float width;

    private Boolean reserved;

}
