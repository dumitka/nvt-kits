package com.backend.springboot.model;

public class Menadzer extends Direktor {
	public Menadzer() {
		super();
	}

	public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
