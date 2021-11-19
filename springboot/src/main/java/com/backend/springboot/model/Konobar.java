package com.backend.springboot.model;

public class Konobar extends KorisnikSistema {
	public Konobar() {
		super();
	}

	public Konobar(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
