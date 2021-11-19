package com.backend.springboot.model;

import java.util.Set;

public class Restoran {
	private Set<Jelovnik> jelovnici;
	private Set<Sto> stolovi;
	private Set<KartaPica> kartePica;
	private Set<Plata> plate;
	private Set<Izvestaj> izvestaji;
	
	public Restoran() {
		
	}
	
	public Restoran(Set<Jelovnik> jelovnici, Set<Sto> stolovi, Set<KartaPica> kartePica, Set<Plata> plate,
			Set<Izvestaj> izvestaji) {
		super();
		this.jelovnici = jelovnici;
		this.stolovi = stolovi;
		this.kartePica = kartePica;
		this.plate = plate;
		this.izvestaji = izvestaji;
	}

	public Set<Jelovnik> getJelovnici() {
		return jelovnici;
	}

	public void setJelovnici(Set<Jelovnik> jelovnici) {
		this.jelovnici = jelovnici;
	}

	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}

	public Set<KartaPica> getKartePica() {
		return kartePica;
	}

	public void setKartePica(Set<KartaPica> kartePica) {
		this.kartePica = kartePica;
	}

	public Set<Plata> getPlate() {
		return plate;
	}

	public void setPlate(Set<Plata> plate) {
		this.plate = plate;
	}

	public Set<Izvestaj> getIzvestaji() {
		return izvestaji;
	}

	public void setIzvestaji(Set<Izvestaj> izvestaji) {
		this.izvestaji = izvestaji;
	}
}
