package com.backend.springboot.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeskSizeDTO {
    float height;
    float width;
    float x;
    float y;
}
