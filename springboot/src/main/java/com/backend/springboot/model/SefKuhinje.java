package com.backend.springboot.model;

public class SefKuhinje extends Kuvar {
	public SefKuhinje() {
		super();
	}

	public SefKuhinje(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
