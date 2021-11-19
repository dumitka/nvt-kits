package com.backend.springboot.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Jelovnik {
	private LocalDateTime pocetakVazenja;
	private Set<CenaJela> ceneJela;
	
	public Jelovnik() {
		
	}
	
	public Jelovnik(LocalDateTime pocetakVazenja, Set<CenaJela> ceneJela) {
		super();
		this.pocetakVazenja = pocetakVazenja;
		this.ceneJela = ceneJela;
	}

	public LocalDateTime getPocetakVazenja() {
		return pocetakVazenja;
	}
	public void setPocetakVazenja(LocalDateTime pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}
	public Set<CenaJela> getCeneJela() {
		return ceneJela;
	}
	public void setCeneJela(Set<CenaJela> ceneJela) {
		this.ceneJela = ceneJela;
	}
}
