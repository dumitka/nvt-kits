package com.backend.springboot.model;

public class Pice {
	private String naziv;
	private String opis;
	private int kolicinaBroj;
	private String kolicinaJedinica;
	private TipPica tipPica;
	private String slika;
	
	public Pice() {
		
	}
	
	public Pice(String naziv, String opis, int kolicinaBroj, String kolicinaJedinica, TipPica tipPica, String slika) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.kolicinaBroj = kolicinaBroj;
		this.kolicinaJedinica = kolicinaJedinica;
		this.tipPica = tipPica;
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getKolicinaBroj() {
		return kolicinaBroj;
	}

	public void setKolicinaBroj(int kolicinaBroj) {
		this.kolicinaBroj = kolicinaBroj;
	}

	public String getKolicinaJedinica() {
		return kolicinaJedinica;
	}

	public void setKolicinaJedinica(String kolicinaJedinica) {
		this.kolicinaJedinica = kolicinaJedinica;
	}

	public TipPica getTipPica() {
		return tipPica;
	}

	public void setTipPica(TipPica tipPica) {
		this.tipPica = tipPica;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
}
