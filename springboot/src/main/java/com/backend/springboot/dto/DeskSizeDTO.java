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
    int height;
    int width;
}
