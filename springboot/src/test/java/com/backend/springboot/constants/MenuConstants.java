package com.backend.springboot.constants;

import java.time.LocalDateTime;

import com.backend.springboot.model.Menu;

public class MenuConstants {
	public static Integer ID_OF_CURRENT_MENU = 2;
	public static Integer ID_OF_DATE_MENU = 1;
	public static LocalDateTime EXISTING_DATE_MENU = LocalDateTime.of(2020, 10, 1, 0, 0);
	public static LocalDateTime NON_EXISTING_DATE_MENU = LocalDateTime.of(2023, 10, 1, 0, 0);
	
	public static Menu CURRENT_MENU = Menu.builder().id(ID_OF_CURRENT_MENU).current(true).dateOfValidation(EXISTING_DATE_MENU).build();
}
