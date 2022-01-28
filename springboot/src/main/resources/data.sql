insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_DIRECTOR');
insert into role (name) values ('ROLE_WAITER');
insert into role (name) values ('ROLE_COOK');
insert into role (name) values ('ROLE_MANAGER');
insert into role (name) values ('ROLE_BARTENDER');
insert into role (name) values ('ROLE_CHEF');
insert into role (name) values ('ROLE_SERVER');

insert into users(username, password, name, last_name, fired, enabled) values ('pera', '$2a$10$hN88.iZa1VSk4Ncxjpt.M.xZ5swoN1EZwryN4oUY/8Uf6wUVEDtaO', 'Petar', 'Peric', false, true); --HESIRATI LOZINKU pera
insert into user_role (user_id, role_id) values (1, 1);--PERA ADMIN

insert into users(username, password, name, last_name, fired, enabled) values ('djuro', '$2a$10$5uZTicSzCSS.myb04Aw/HetoRRqoR3ncBqdQc1zk.vPFDQxcvTNaW', 'Djuro', 'Djuric', false, true); --HESIRANA LOZINKA djuro
insert into user_role (user_id, role_id) values (2, 4);--djuro kuvar

insert into users(username, password, name, last_name, fired, enabled) values ('toma', '$2a$10$PlQAYQpW1urv/u405Xqx4eQ9LoIEFTImVtyKkyaknmG4pStbItFee', 'Toma', 'Zdravkovic', false, true); --HESIRANA LOZINKA toma
insert into user_role (user_id, role_id) values (3, 3);-- Toma konobar

insert into users(username, password, name, last_name, fired, enabled) values ('mile', '$2a$10$sPq2onK/ogVP10ElHxk/SuYbdreHo09awAHgGUJP3zyHxb9OBZt0a', 'Mile', 'Car', false, true); --HESIRANA LOZINKA mile
insert into user_role (user_id, role_id) values (4, 2);-- Mile direktore

insert into users(username, password, name, last_name, fired, enabled) values ('mara', '$2a$10$JG6rla1abqWBRGZUqKa5fOssXQW0oWy3kLtgVcmrrNk6fXL7A6MLy', 'Mara', 'Buba', false, true); --HESIRANA LOZINKA mara
insert into user_role (user_id, role_id) values (5, 7);-- Mara sefica kuhinje girl power yes

insert into users(username, password, name, last_name, fired, enabled) values ('drda', '$2a$10$SaDEzkQC/nrHjKCz/Ukd7eRD.dIcYmVeAchJjVCTVbcgjNsSsfCNG', 'Dragan', 'Dakovic', false, true); --HESIRANA LOZINKA drda
insert into user_role (user_id, role_id) values (6, 5);-- Menadzer Drda

insert into users(username, password, name, last_name, fired, enabled) values ('lola', '$2a$10$qHfUan30YwzEzvI09ZFd/eUrvP8IVbpmJDOjbam8FT53SzEjgolb2', 'Lola', 'Lolic', false, true); --HESIRANA LOZINKA lola
insert into user_role (user_id, role_id) values (7, 6);-- Lola sanker

insert into users(username, password, name, last_name, fired, enabled) values ('laki', '$2a$10$4E0EFy5UWzpnU2iVQLwJfuWEuVQsXEfUNE.cdEiWfJyEbg85cac4O', 'Laka', 'Lakic', false, true); --HESIRANA LOZINKA laki
insert into user_role (user_id, role_id) values (8, 8);-- Laki sef sale

insert into users(username, password, name, last_name, fired, enabled) values ('tozovac', '$2a$10$KdkAnIk4dlbxwMf.SOuW0u0z5OOhNhZq4NRL7qBq2KABIUGUeDZF2', 'Predrag', 'Zivkovic', false, true); --HESIRANA LOZINKA tozo
insert into user_role (user_id, role_id) values (9, 3);-- Laki sef sale

-- RESTAURANT
insert into restaurant (id) values (1);

--MEALS
--COLD APPETIZER (1-6)
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Gurmanska punjena jaja', 0, 'Jaja, Majoneza, Senf, Krastavčići, Ljuta paprika', 0, 15, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Slana rozen torta', 0, 'Šunka, Sir, Pavlaka, Majoneza, Krastavčići, Ajvar', 1, 45, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Štapići sa makom', 0, 'Sir, Mak, Sjemenke', 0, 30, 100, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Projice sa špinatom', 0, 'Špinat, Sir, Kukuruzno brašno', 0, 30, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Hladna salata', 0, 'Piletina, Feta sir, Pavlaka, Krastavčići', 0, 20, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Sendvič', 0, 'Piletina, Dimljeni vrat, Zelena salata, Biljni sir', 0, 5, 250, 'g', 'nema', false);

--HOT APPETIZER (7-12)
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Juneća čorba', 1, 'Juneće meso, Brokoli, Mrkva, Rezanci', 0, 30, 330, 'ml', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Punjene kajzerice', 1, 'Kajzerice, Jaja, Umak, Tvrdi sir, Dimljeni vrat', 0, 30, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Corn dog', 1, 'Viršle, Kukuruzno brasno', 0, 20, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Omlet sa dimljenom slaninom', 1, 'Jaja, Dimljena mesnata slanina, Sir', 0, 15, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Uvijena pita sa sirom', 1, 'Sir, Kajmak', 1, 45, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Slane američke palačinke', 1, 'Feta sir', 0, 30, 180, 'g', 'nema', false);

--MAIN MEAL (13-18)
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Piletina u čili sosu', 2, 'Piletina, Paradaiz sos, Čili', 0, 30, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Đuveč sa kobasicom', 2, 'Kobasice, Riža, Povrće za đuveč', 1, 45, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Milanske šnicle', 2, 'Šnicle, Paradaiz sok, Paradaiz', 1, 40, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Musaka na kockice', 2, 'Kropmir, Mljeveno meso, Crni luk, Jogurt, Maslac', 1, 40, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Piletina sa šampinjonima', 2, 'Piletina, Šampinjoni, Pavlaka, Jaja, Pirinač', 2, 60, 300, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Krompir sa mesom', 2, 'Crni luk, Piletina, Krompir, Mrkva', 2, 60, 330, 'g', 'nema', false);

--DESERT (19-24)
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Baklava', 3, 'Orasi, Bademi, Med', 2, 60, 180, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Ormašice', 3, 'Orasi, Bademi, Med', 2, 50, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Toffe torta', 3, 'Karamela, Plazma', 1, 40, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kolač Atina', 3, 'Lješnjak, Čokolada, Eurokrem', 1, 60, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Boem kolač', 3, 'Čokoladne kore, Vanilija, Orasi, Crna čokolada', 2, 70, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('San', 3, 'Čokoladne kore, tamna čokolada, Med', 2, 90, 180, 'g', 'nema', false);

--SALAT (25-30)
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Salata sa rotkvicom', 4, 'Zelena salata, Rotkvica, Crni luk', 0, 10, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Ruska salata', 4, 'Šunka, Povrće, Majoneza, Jaja', 1, 45, 180, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kupus salata', 4, 'Kupus', 0, 10, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Sezonska salata', 4, 'Paradaiz, Krastavci, Luk', 0, 10, 180, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Francuska salata', 4, 'Piletina, Povrće, Majoneza, Jaja', 0, 50, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Cvekla', 4, 'Kisela cvekla', 0, 10, 180, 'g', 'nema', false);

--APPENDICES (31-32)
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pomfrit', 5, '', 0, 10, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Čips', 5, '', 0, 10, 150, 'g', 'nema', false);


--MEAL PRICES
--COLD APPETIZER
insert into meal_prices(meal_id, price, deleted) values (1, 300, false);
insert into meal_prices(meal_id, price, deleted) values (2, 280, false);
insert into meal_prices(meal_id, price, deleted) values (3, 200, false);
insert into meal_prices(meal_id, price, deleted) values (4, 200, false);

--HOT APPETIZER
insert into meal_prices(meal_id, price, deleted) values (7, 350, false);
insert into meal_prices(meal_id, price, deleted) values (8, 300, false);
insert into meal_prices(meal_id, price, deleted) values (9, 180, false);
insert into meal_prices(meal_id, price, deleted) values (11, 230, false);

--MAIN MEAL
insert into meal_prices(meal_id, price, deleted) values (13, 480, false);
insert into meal_prices(meal_id, price, deleted) values (15, 500, false);
insert into meal_prices(meal_id, price, deleted) values (17, 480, false);
insert into meal_prices(meal_id, price, deleted) values (18, 590, false);

--DESERT
insert into meal_prices(meal_id, price, deleted) values (19, 300, false);
insert into meal_prices(meal_id, price, deleted) values (20, 350, false);
insert into meal_prices(meal_id, price, deleted) values (22, 370, false);
insert into meal_prices(meal_id, price, deleted) values (23, 320, false);

--SALAT
insert into meal_prices(meal_id, price, deleted) values (26, 200, false);
insert into meal_prices(meal_id, price, deleted) values (27, 230, false);
insert into meal_prices(meal_id, price, deleted) values (28, 180, false);
insert into meal_prices(meal_id, price, deleted) values (29, 300, false);

--APPENDICES
insert into meal_prices(meal_id, price, deleted) values (31, 200, false);
insert into meal_prices(meal_id, price, deleted) values (32, 200, false);

--MENU 1
insert into menus (date_Of_Validation, current, restaurant_id) values ('2021-11-01 00:00'::timestamp, true, 1);

--MENU_MEAL_PRICE
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 1);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 2);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 3);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 4);

insert into menus_meal_prices (menu_id, meal_price_id) values (1, 5);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 6);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 7);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 8);

insert into menus_meal_prices (menu_id, meal_price_id) values (1, 9);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 10);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 11);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 12);

insert into menus_meal_prices (menu_id, meal_price_id) values (1, 13);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 14);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 15);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 16);

insert into menus_meal_prices (menu_id, meal_price_id) values (1, 17);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 18);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 19);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 20);

insert into menus_meal_prices (menu_id, meal_price_id) values (1, 21);
insert into menus_meal_prices (menu_id, meal_price_id) values (1, 22);



-- DRINK
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Sprajt', 0, 'Gazirano pice', 0.2, 'l', true, 'pice.png');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Koka kola', 0, 'Gazirano pice', 0.2, 'l', true, 'pice.png');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Jabuka', 1, 'Nektar sok od jabuke. Negazirano.', 0.2, 'l', true, 'sok.jpg');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Limunada', 1, 'Sveze cedjen sok od limuna', 0.2, 'l', true, 'sok.jpg');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Vino', 2, 'Gazirano pice', 0.2, 'l', true, 'vino.jpg');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Jelen', 2, 'Jelen pivo - veliko', 0.5, 'l', true, 'pivo.jpg');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Jelen', 2, 'Jelen pivo - malo', 0.3, 'l', true, 'pivo.jpg');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Caj', 3, 'Milfordov caj', 0.2, 'l', true, 'caj.png');
insert into drink (name, type, description, amount_number, amount_unit, available, image)
    values ('Kafa', 3, 'Domaca kafa', 0.2, 'l', true, 'kafa.jpg');

-- DRINK_PRICE
insert into drink_price (price, drink_id) values ('120', 2);
insert into drink_price (price, drink_id) values ('100', 3);
insert into drink_price (price, drink_id) values ('150', 4);
insert into drink_price (price, drink_id) values ('130', 5);
insert into drink_price (price, drink_id) values ('90', 6);
insert into drink_price (price, drink_id) values ('100', 7);

-- DRINK_CARD
insert into drink_card (date_Of_Validation, restaurant_id) values ('1995-05-25 06:10'::timestamp, 1);
insert into drink_card (date_Of_Validation, restaurant_id) values ('1999-10-28 06:10'::timestamp, 1);

-- DRINK_CARD -> DRINK_PRICE
insert into drink_prices (drink_card_id, drink_price_id) values (1, 1);
insert into drink_prices (drink_card_id, drink_price_id) values (1, 2);
insert into drink_prices (drink_card_id, drink_price_id) values (2, 1);
insert into drink_prices (drink_card_id, drink_price_id) values (2, 2);
insert into drink_prices (drink_card_id, drink_price_id) values (2, 3);
insert into drink_prices (drink_card_id, drink_price_id) values (2, 4);
insert into drink_prices (drink_card_id, drink_price_id) values (2, 5);
insert into drink_prices (drink_card_id, drink_price_id) values (2, 6);

-- DESK
insert into desk (desk_status, tip, x, y, height, width, reserved, table_num) values (0, 0.0, 10, 10, 100, 100, false, 0);
insert into desk (desk_status, tip, x, y, height, width, reserved, table_num) values (0, 0.0, 10, 80, 50, 50, false, 0);

-- ORDER
insert into orders (deleted, desk_id) values (false, 1);

-- ORDERED DRINK
insert into ordered_drink (amount, status, user_id, drink_id, order_id) values (2, 0, null, 1, 1);
