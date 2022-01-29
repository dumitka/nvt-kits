package com.backend.springboot.constants;

import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.model.User;
import com.backend.springboot.service.OrderedDrinkService;

public class OrderedDrinkConstants {
    public static final OrderedDrink DRINK = OrderedDrink.builder()
            .id(2)
            .bartender(User.builder().id(3).build())
            .build();
}
