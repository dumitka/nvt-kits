-- ROLE
insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_DIRECTOR');
insert into role (name) values ('ROLE_WAITER');
insert into role (name) values ('ROLE_COOK');
insert into role (name) values ('ROLE_MANAGER');
insert into role (name) values ('ROLE_BARTENDER');
insert into role (name) values ('ROLE_CHEF');
insert into role (name) values ('ROLE_SERVER');

--USER
insert into users(username, password, name, last_name, fired, enabled)
    values ('masa', '$2a$10$INVaw70gvhwlo9eF2ctMpO8vDZx9q35o43GXsnVRhxklTKK5L6YV2', 'Magdalena', 'Gavrilovic', false, true);
insert into users(username, password, name, last_name, fired, enabled)
    values ('otpusteni', '$2a$10$INVaw70gvhwlo9eF2ctMpO8vDZx9q35o43GXsnVRhxklTKK5L6YV2', 'Marko', 'Markovic', true, true);
insert into users(username, password, name, last_name, fired, enabled   )-- sifra pera
    values ('sef-sale', '$2a$10$hN88.iZa1VSk4Ncxjpt.M.xZ5swoN1EZwryN4oUY/8Uf6wUVEDtaO', 'Pera', 'Peric', false, true);
insert into users(username, password, name, last_name, fired, enabled   )-- sifra pera
    values ('kuvar', '$2a$10$hN88.iZa1VSk4Ncxjpt.M.xZ5swoN1EZwryN4oUY/8Uf6wUVEDtaO', 'Pesa', 'Desa', false, true);

-- USER -> ROLES
insert into user_role (user_id, role_id) values (1, 7);
insert into user_role (user_id, role_id) values (2, 6);
insert into user_role (user_id, role_id) values (3, 8);
insert into user_role (user_id, role_id) values (4, 4);

-- RESTAURANT
insert into restaurant (id) values (1);

-- DRINK
insert into drink (id, name, type, description, amount_number, amount_unit, available, image)
    values (1, 'caj', 3, 'Milfordov caj', 0.2, 'l', true, 'caj.jpg');
insert into drink (id, name, type, description, amount_number, amount_unit, available, image)
    values (2, 'kafa', 3, 'Domaca kafa', 0.2, 'l', true, 'kafa.jpg');

-- DRINK_PRICE
insert into drink_price (id, price, drink_id) values (1, '150', 1);
insert into drink_price (id, price, drink_id) values (2, '170', 2);

-- DRINK_CARD
insert into drink_card (id, date_Of_Validation, restaurant_id) values (1, '1995-05-25 06:10'::timestamp, 1);

-- DRINK_CARD -> DRINK_PRICE
insert into drink_prices (drink_card_id, drink_price_id) values (1, 1);
insert into drink_prices (drink_card_id, drink_price_id) values (1, 2);


-- MEAL
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kajgana', 1, 'Domace, zdravo', 0, 5, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Vocna salata', 3, 'Osvjezavajuce', 0, 10, 300, 'g', 'nema', true);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pasulj', 2, 'Jako', 2, 180, 330, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pecenje', 2, 'Jako', 2, 300, 1, 'kg', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Supa', 1, 'Toplo', 1, 30, 200, 'ml', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Hladno', 0, 'Toplo', 1, 30, 200, 'ml', 'nema', false);


--MEAL_PRICE
insert into meal_prices(meal_id, price, deleted) values (1, 300, false);
insert into meal_prices(meal_id, price, deleted) values (2, 330, false);
insert into meal_prices(meal_id, price, deleted) values (3, 480, false);
insert into meal_prices(meal_id, price, deleted) values (4, 700, false);


--MENU
insert into menus (id, date_Of_Validation, current, restaurant_id) values (1, '2020-10-01 00:00'::timestamp, false, 1);
insert into menus (id, date_Of_Validation, current, restaurant_id) values (2, '2021-04-01 00:00'::timestamp, true, 1);
insert into menus (id, date_Of_Validation, current, restaurant_id) values (3, '2022-10-01 00:00'::timestamp, false, 1);


--MENU_MEAL_PRICE
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 1);
insert into menus_meal_prices (menu_id, meal_price_id) values (2, 1);
insert into menus_meal_prices (menu_id, meal_price_id) values (2, 3);
insert into menus_meal_prices (menu_id, meal_price_id) values (2, 4);
insert into menus_meal_prices (menu_id, meal_price_id) values (3, 4);
insert into menus_meal_prices (menu_id, meal_price_id) values (3, 2);

-- DESK
insert into desk (desk_status, tip, x, y, height, width, reserved, deleted) values (1, 0.0, 0.1, 0.1, 0.2, 0.2, false, false);
insert into desk (desk_status, tip, x, y, height, width, reserved, deleted) values (1, 0.0, 0.5, 0.5, 0.15, 0.15, false, false);

-- ORDER
insert into orders (user_id, desk_id, deleted) values (3, 1, false);
insert into orders (deleted, desk_id) values (false, 2);

--ORDERED MEAL  --za sada nek bude na 3 user_id kao toma konobar, treba da prodje
insert into ordered_meal (amount, status, user_id, meal_id, order_id) values (2, 0, 3, 3, 1);
insert into ordered_meal (amount, status, user_id, meal_id, order_id) values (1, 0, 3, 1, 1);
insert into ordered_meal (amount, status, user_id, meal_id, order_id) values (1, 0, 3, 1, 2);
insert into ordered_meal (amount, status, user_id, meal_id, order_id) values (1, 0, 3, 1, 2);
insert into ordered_meal (amount, status, user_id, meal_id, order_id) values (1, 0, 3, 2, 2);

-- NOTIFICATION
insert into notification (status, message) values (0, 'poruka 1 - poslata');
insert into notification (status, message) values (1, 'poruka 2 - dostavljena');
insert into notification (status, message) values (2, 'poruka 3 - vidjena');

--ORDERED DRINKS
insert into ordered_drink (amount, status, user_id, drink_id, order_id) values (2, 0, 2, 1, 1); --ordered
insert into ordered_drink (amount, status, user_id, drink_id, order_id) values (1, 1, 2, 2, 1); --in progress

-- SALARY
insert into salary (user_id, amount, date_Of_Validation) values (1, 10000, '2020-03-04');
insert into salary (user_id, amount, date_Of_Validation) values (1, 14000, '2021-03-04');
insert into salary (user_id, amount, date_Of_Validation) values (2, 20000, '2020-05-04');
insert into salary (user_id, amount, date_Of_Validation) values (3, 10000, '2020-03-04');
