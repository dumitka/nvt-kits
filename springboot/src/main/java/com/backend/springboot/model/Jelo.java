package com.backend.springboot.model;

public class Jelo {
	private String naziv;
	private TipJela tipJela;
	private String opis;
	private TezinaSpremanja tezinaSpremanja;
	private int vremeSpremanja;
	private int kolicinaBroj;
	private String kolicinaJedinica;
	private String slika;
	
	public Jelo() {
		
	}
	
	public Jelo(String naziv, TipJela tipJela, String opis, TezinaSpremanja tezinaSpremanja, int vremeSpremanja,
			int kolicinaBroj, String kolicinaJedinica, String slika) {
		super();
		this.naziv = naziv;
		this.tipJela = tipJela;
		this.opis = opis;
		this.tezinaSpremanja = tezinaSpremanja;
		this.vremeSpremanja = vremeSpremanja;
		this.kolicinaBroj = kolicinaBroj;
		this.kolicinaJedinica = kolicinaJedinica;
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipJela getTipJela() {
		return tipJela;
	}

	public void setTipJela(TipJela tipJela) {
		this.tipJela = tipJela;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public TezinaSpremanja getTezinaSpremanja() {
		return tezinaSpremanja;
	}

	public void setTezinaSpremanja(TezinaSpremanja tezinaSpremanja) {
		this.tezinaSpremanja = tezinaSpremanja;
	}

	public int getVremeSpremanja() {
		return vremeSpremanja;
	}

	public void setVremeSpremanja(int vremeSpremanja) {
		this.vremeSpremanja = vremeSpremanja;
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

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
}
