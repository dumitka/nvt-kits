package com.backend.springboot.model;

public class Admin extends KorisnikSistema {
	public Admin() {
		super();
	}

	public Admin(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
