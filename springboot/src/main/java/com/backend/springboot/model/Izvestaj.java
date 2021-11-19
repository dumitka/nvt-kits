package com.backend.springboot.model;

import java.time.LocalDateTime;

public class Izvestaj {
	private TipIzvestaja tipIzvestaja;
	private LocalDateTime datum;
	private float trosakNaPlate;
	private float trosakNabavke;
	private float trosakNaRezije;
	private float prihodOdProdajeJela;
	private float prihodOdProdajePica;
	private float baksis;
	
	public Izvestaj() {
		
	}
	
	public Izvestaj(TipIzvestaja tipIzvestaja, LocalDateTime datum, float trosakNaPlate, float trosakNabavke,
			float trosakNaRezije, float prihodOdProdajeJela, float prihodOdProdajePica, float baksis) {
		super();
		this.tipIzvestaja = tipIzvestaja;
		this.datum = datum;
		this.trosakNaPlate = trosakNaPlate;
		this.trosakNabavke = trosakNabavke;
		this.trosakNaRezije = trosakNaRezije;
		this.prihodOdProdajeJela = prihodOdProdajeJela;
		this.prihodOdProdajePica = prihodOdProdajePica;
		this.baksis = baksis;
	}

	public TipIzvestaja getTipIzvestaja() {
		return tipIzvestaja;
	}

	public void setTipIzvestaja(TipIzvestaja tipIzvestaja) {
		this.tipIzvestaja = tipIzvestaja;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public float getTrosakNaPlate() {
		return trosakNaPlate;
	}

	public void setTrosakNaPlate(float trosakNaPlate) {
		this.trosakNaPlate = trosakNaPlate;
	}

	public float getTrosakNabavke() {
		return trosakNabavke;
	}

	public void setTrosakNabavke(float trosakNabavke) {
		this.trosakNabavke = trosakNabavke;
	}

	public float getTrosakNaRezije() {
		return trosakNaRezije;
	}

	public void setTrosakNaRezije(float trosakNaRezije) {
		this.trosakNaRezije = trosakNaRezije;
	}

	public float getPrihodOdProdajeJela() {
		return prihodOdProdajeJela;
	}

	public void setPrihodOdProdajeJela(float prihodOdProdajeJela) {
		this.prihodOdProdajeJela = prihodOdProdajeJela;
	}

	public float getPrihodOdProdajePica() {
		return prihodOdProdajePica;
	}

	public void setPrihodOdProdajePica(float prihodOdProdajePica) {
		this.prihodOdProdajePica = prihodOdProdajePica;
	}

	public float getBaksis() {
		return baksis;
	}

	public void setBaksis(float baksis) {
		this.baksis = baksis;
	}
}
