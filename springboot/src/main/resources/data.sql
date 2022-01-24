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


--MEALS
--COLD APPETIZER
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Gurmanska punjena jaja', 0, 'Jaja, Majoneza, Senf, Krastavcici, Ljuta paprika', 0, 15, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Slana rozen torta', 0, 'Sunka, Sir, Pavlaka, Majoneza, Krastavcici, Ajvar', 1, 45, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Stapici sa makom', 0, 'Sir, Mak, Sjemenke', 0, 30, 100, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Projice sa spinatom', 0, 'Spinat, Sir, Kukuruzno brasno', 0, 30, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Hladna salata', 0, 'Piletina, Feta sir, Pavlaka, Krastavcici', 0, 20, 250, 'g', 'nema', false);

--HOT APPETIZER
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Juneca corba', 1, 'Junece meso, Brokoli, Mrkva, Rezanci', 0, 30, 330, 'ml', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Punjene kajzerice', 1, 'Kajzerice, Jaja, Umak, Tvrdi sir, Dimljeni vrat', 0, 30, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Corn dog', 1, 'Virsle, Kukuruzno brasno', 0, 20, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Omlet sa dimljenom slaninom', 1, 'Jaja, Dimljena mesnata slanina, Sir', 0, 15, 150, 'g', 'nema', false);

--MAIN MEAL
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Piletina u čili sosu', 2, 'Piletina, Paradaiz sos, Čili', 0, 30, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Đuveč sa kobasicom', 2, 'Kobasice, Riža, Povrće za đuveč', 1, 45, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Milanske šnicle', 2, 'Šnicle, Paradaiz sok, Paradaiz', 1, 40, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Musaka na kockice', 2, 'Kropmir, Mljeveno meso, Crni luk, Jogurt, Maslac', 1, 40, 250, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Piletina sa šampinjonima', 2, 'Piletina, Šampinjoni, Pavlaka, Jaja, Pirinač', 2, 60, 300, 'g', 'nema', false);

--DESERT
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Toffe torta', 3, 'Karamela, Plazma', 1, 40, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kolač Atina', 3, 'Lješnjak, Čkolada, Eurokrem', 1, 60, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Boem kolac', 3, 'Cokoladne kore, Vanilija, Orasi, Crna cokolada', 2, 70, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('San', 3, 'Cokoladne kore, tamna cokolada, Med', 2, 90, 180, 'g', 'nema', false);


--SALAT
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Salata sa rotkvicom', 4, 'Zelena salata, Rotkvica, Crni luk', 0, 10, 150, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Ruska salata', 4, 'Sunka, Povrce, Majoneza, Jaja', 0, 30, 180, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kupus salata', 4, '', 0, 10, 200, 'g', 'nema', false);
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Sezonska salata', 4, 'Paradaiz, Krastavci, Luk', 0, 10, 180, 'g', 'nema', false);


--APPENDICES
insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pomfrit', 5, '', 0, 10, 150, 'g', 'nema', false);




