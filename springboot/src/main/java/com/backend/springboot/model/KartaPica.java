package com.backend.springboot.model;

import java.time.LocalDateTime;
import java.util.Set;

public class KartaPica {
	private LocalDateTime pocetakVazenja;
	private Set<CenaPica> cenePica;
	
	public KartaPica() {
		
	}
	
	public KartaPica(LocalDateTime pocetakVazenja, Set<CenaPica> cenePica) {
		super();
		this.pocetakVazenja = pocetakVazenja;
		this.cenePica = cenePica;
	}
	
	public LocalDateTime getPocetakVazenja() {
		return pocetakVazenja;
	}
	public void setPocetakVazenja(LocalDateTime pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}
	public Set<CenaPica> getCenePica() {
		return cenePica;
	}
	public void setCenePica(Set<CenaPica> cenePica) {
		this.cenePica = cenePica;
	}
}
