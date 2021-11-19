package com.backend.springboot.model;

public class Kuvar extends KorisnikSistema {
	public Kuvar() {
		super();
	}

	public Kuvar(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}	
}
