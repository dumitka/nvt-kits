package com.backend.springboot.model;

public class Sanker extends KorisnikSistema {
	public Sanker() {
		super();
	}

	public Sanker(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
