package com.backend.springboot.model;

public class KorisnikSistema {
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private TipKorisnika tipKorisnika;
	private boolean otpusten;
	
	public KorisnikSistema() {
		
	}

	public KorisnikSistema(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
			boolean otpusten) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.tipKorisnika = tipKorisnika;
		this.otpusten = otpusten;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public boolean isOtpusten() {
		return otpusten;
	}

	public void setOtpusten(boolean otpusten) {
		this.otpusten = otpusten;
	}
}
