package com.backend.springboot.model;

public class Obavestenje {
	private StatusObavestenja status;
	private String poruka;
	private Porudzbina porudzbina;
	
	public Obavestenje() {
		
	}

	public Obavestenje(StatusObavestenja status, String poruka, Porudzbina porudzbina) {
		super();
		this.status = status;
		this.poruka = poruka;
		this.porudzbina = porudzbina;
	}

	public StatusObavestenja getStatus() {
		return status;
	}

	public void setStatus(StatusObavestenja status) {
		this.status = status;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
}
