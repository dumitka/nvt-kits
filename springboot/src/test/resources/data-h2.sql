
insert into users(username, password, name, last_name, fired, enabled) values ('masa', '$2a$10$INVaw70gvhwlo9eF2ctMpO8vDZx9q35o43GXsnVRhxklTKK5L6YV2', 'Magdalena', 'Gavrilovic', false, true); 

insert into users(username, password, name, last_name, fired, enabled) values ('otpusteni', '$2a$10$INVaw70gvhwlo9eF2ctMpO8vDZx9q35o43GXsnVRhxklTKK5L6YV2', 'Marko', 'Markovic', true, true);


insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kajgana', 1, 'Domace, zdravo', 0, 5, 200, 'g', 'nema', false);
insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Vocna salata', 3, 'Osvjezavajuce', 0, 10, 300, 'g', 'nema', true);
insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pasulj', 2, 'Jako', 2, 180, 330, 'g', 'nema', false);
insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pecenje', 2, 'Jako', 2, 300, 1, 'kg', 'nema', false);

insert into meal_price(meal_id, price_amount) values (1, 300);
insert into meal_price(meal_id, price_amount) values (2, 330);
insert into meal_price(meal_id, price_amount) values (3, 480);
insert into meal_price(meal_id, price_amount) values (4, 700);

-- RESTAURANT
insert into restaurant (id) values (1);

-- DRINK
insert into drink (id, name, type, description, amount_number, amount_unit, available, image)
    values (7, 'caj', 3, 'Milfordov caj', 0.2, 'l', true, 'caj.jpg');
insert into drink (id, name, type, description, amount_number, amount_unit, available, image)
    values (8, 'kafa', 3, 'Domaca kafa', 0.2, 'l', true, 'kafa.jpg');

-- DRINK_PRICE
insert into drink_price (id, price, drink_id) values (1, '150', 7);
insert into drink_price (id, price, drink_id) values (2, '170', 8);

-- DRINK_CARD
insert into drink_card (id, date_Of_Validation, restaurant_id) values (1, '2005-05-25 06:10'::timestamp, 1);

-- DRINK_CARD -> DRINK_PRICE
insert into drink_prices (drink_card_id, drink_price_id) values (1, 1);
insert into drink_prices (drink_card_id, drink_price_id) values (1, 2);
