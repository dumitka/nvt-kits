package com.backend.springboot.model;

public class Direktor extends KorisnikSistema {
	public Direktor() {
		super();
	}

	public Direktor(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
