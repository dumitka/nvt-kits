package com.backend.springboot.model;

public class PorucenoPice {
	private int kolicina;
	private Pice pice;
	private Porudzbina porudzbina;
	private Sanker sanker;
	
	public PorucenoPice() {
		
	}

	public PorucenoPice(int kolicina, Pice pice, Porudzbina porudzbina, Sanker sanker) {
		super();
		this.kolicina = kolicina;
		this.pice = pice;
		this.porudzbina = porudzbina;
		this.sanker = sanker;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Pice getPice() {
		return pice;
	}

	public void setPice(Pice pice) {
		this.pice = pice;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public Sanker getSanker() {
		return sanker;
	}

	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}
}
