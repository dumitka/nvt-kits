package com.backend.springboot.model;

public class CenaPica {
	private Pice pice;
	private float iznos;
	
	public CenaPica() {
		
	}
	
	public CenaPica(Pice pice, float iznos) {
		super();
		this.pice = pice;
		this.iznos = iznos;
	}

	public Pice getPice() {
		return pice;
	}

	public void setPice(Pice pice) {
		this.pice = pice;
	}

	public float getIznos() {
		return iznos;
	}

	public void setIznos(float iznos) {
		this.iznos = iznos;
	}
}
