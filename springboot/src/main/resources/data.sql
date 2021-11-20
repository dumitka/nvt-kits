insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_DIREKTOR');
insert into role (name) values ('ROLE_KONOBAR');
insert into role (name) values ('ROLE_KUVAR');
insert into role (name) values ('ROLE_MENADZER');
insert into role (name) values ('ROLE_SANKER');
insert into role (name) values ('ROLE_SEF_KUHINJE');
insert into role (name) values ('ROLE_SEF_SALE');

insert into korisnik(korisnicko_ime, lozinka, ime, prezime, otpusten, enabled) values ('pera', 'pera', 'Petar', 'Peric', false, true); --HESIRATI LOZINKU
insert into korisnik_role (korisnik_id, role_id) values (1, 1);--PERA ADMIN


