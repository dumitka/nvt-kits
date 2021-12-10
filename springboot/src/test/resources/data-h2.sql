
insert into users(username, password, name, last_name, fired, enabled) values ('masa', '$2a$10$INVaw70gvhwlo9eF2ctMpO8vDZx9q35o43GXsnVRhxklTKK5L6YV2', 'Magdalena', 'Gavrilovic', false, true); 



insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kajgana', 1, 'Domace, zdravo', 0, 5, 200, 'g', 'nema', false);
insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Vocna salata', 3, 'Osvjezavajuce', 0, 10, 300, 'g', 'nema', true);
insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pasulj', 2, 'Jako', 2, 180, 330, 'g', 'nema', false);
insert into meal(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pecenje', 2, 'Jako', 2, 300, 1, 'kg', 'nema', false);

insert into meal_price(meal_id, price_amount) values (1, 300);
insert into meal_price(meal_id, price_amount) values (2, 330);
insert into meal_price(meal_id, price_amount) values (3, 480);
insert into meal_price(meal_id, price_amount) values (4, 700);

