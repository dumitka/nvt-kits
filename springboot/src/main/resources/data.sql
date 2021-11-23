insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_DIRECTOR');
insert into role (name) values ('ROLE_WAITER');
insert into role (name) values ('ROLE_COOK');
insert into role (name) values ('ROLE_MANAGER');
insert into role (name) values ('ROLE_BARTENDER');
insert into role (name) values ('ROLE_CHEF');
insert into role (name) values ('ROLE_HALL_MANAGER');

insert into users(username, password, name, last_name, fired, enabled) values ('pera', 'pera', 'Petar', 'Peric', false, true); --HESIRATI LOZINKU
insert into user_role (user_id, role_id) values (1, 1);--PERA ADMIN


