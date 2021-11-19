package com.backend.springboot.model;

import java.time.LocalDateTime;

public class Plata {
	private float iznos;
	private LocalDateTime pocetakVazenja;
	private KorisnikSistema korisnik;
	
	public Plata() {
		
	}
	
	public Plata(float iznos, LocalDateTime pocetakVazenja, KorisnikSistema korisnik) {
		super();
		this.iznos = iznos;
		this.pocetakVazenja = pocetakVazenja;
		this.korisnik = korisnik;
	}

	public float getIznos() {
		return iznos;
	}

	public void setIznos(float iznos) {
		this.iznos = iznos;
	}

	public LocalDateTime getPocetakVazenja() {
		return pocetakVazenja;
	}

	public void setPocetakVazenja(LocalDateTime pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}

	public KorisnikSistema getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikSistema korisnik) {
		this.korisnik = korisnik;
	}
}
