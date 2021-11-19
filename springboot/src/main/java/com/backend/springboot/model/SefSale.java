package com.backend.springboot.model;

public class SefSale extends Konobar  {
	public SefSale() {
		super();
	}

	public SefSale(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
