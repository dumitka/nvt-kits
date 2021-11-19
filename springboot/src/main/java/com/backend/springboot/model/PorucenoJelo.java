package com.backend.springboot.model;

public class PorucenoJelo {
	private int kolicna;
	private Jelo jelo;
	private Porudzbina porudzbina;
	private Kuvar kuvar;
	
	public PorucenoJelo() {
		
	}
	
	public PorucenoJelo(int kolicna, Jelo jelo, Porudzbina porudzbina, Kuvar kuvar) {
		super();
		this.kolicna = kolicna;
		this.jelo = jelo;
		this.porudzbina = porudzbina;
		this.kuvar = kuvar;
	}
	
	public int getKolicna() {
		return kolicna;
	}
	
	public void setKolicna(int kolicna) {
		this.kolicna = kolicna;
	}
	
	public Jelo getJelo() {
		return jelo;
	}
	
	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}
	
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}
	
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	
	public Kuvar getKuvar() {
		return kuvar;
	}
	
	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}
}
