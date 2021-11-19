package com.backend.springboot.model;

public class CenaJela {
	private Jelo jelo;
	private float iznos;
	
	public CenaJela() {
		
	}
	
	public CenaJela(Jelo jelo, float iznos) {
		super();
		this.jelo = jelo;
		this.iznos = iznos;
	}

	public Jelo getJelo() {
		return jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	public float getIznos() {
		return iznos;
	}

	public void setIznos(float iznos) {
		this.iznos = iznos;
	}
}
