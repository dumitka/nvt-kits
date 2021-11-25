insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_DIRECTOR');
insert into role (name) values ('ROLE_WAITER');
insert into role (name) values ('ROLE_COOK');
insert into role (name) values ('ROLE_MANAGER');
insert into role (name) values ('ROLE_BARTENDER');
insert into role (name) values ('ROLE_CHEF');
insert into role (name) values ('ROLE_HALL_MANAGER');

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