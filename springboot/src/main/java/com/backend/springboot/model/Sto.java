package com.backend.springboot.model;

public class Sto {
	private StatusStola statusStola;
	private float baksis;
	private boolean rezervisan;
	
	public Sto() {
		
	}
	
	public Sto(StatusStola statusStola, float baksis, boolean rezervisan) {
		super();
		this.statusStola = statusStola;
		this.baksis = baksis;
		this.rezervisan = rezervisan;
	}

	public StatusStola getStatusStola() {
		return statusStola;
	}

	public void setStatusStola(StatusStola statusStola) {
		this.statusStola = statusStola;
	}

	public float getBaksis() {
		return baksis;
	}

	public void setBaksis(float baksis) {
		this.baksis = baksis;
	}

	public boolean isRezervisan() {
		return rezervisan;
	}

	public void setRezervisan(boolean rezervisan) {
		this.rezervisan = rezervisan;
	}
}
