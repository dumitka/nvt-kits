package com.backend.springboot.model;

import java.util.Set;

public class Porudzbina {
	private Set<PorucenoJelo> porucenaJela;
	private Set<PorucenoPice> porucenaPica;
	private Konobar konobar;
	private Set<Obavestenje> obavestenja;
	private Sto sto;
	
	public Porudzbina() {
		
	}

	public Porudzbina(Set<PorucenoJelo> porucenaJela, Set<PorucenoPice> porucenaPica, Konobar konobar,
			Set<Obavestenje> obavestenja, Sto sto) {
		super();
		this.porucenaJela = porucenaJela;
		this.porucenaPica = porucenaPica;
		this.konobar = konobar;
		this.obavestenja = obavestenja;
		this.sto = sto;
	}

	public Set<PorucenoJelo> getPorucenaJela() {
		return porucenaJela;
	}

	public void setPorucenaJela(Set<PorucenoJelo> porucenaJela) {
		this.porucenaJela = porucenaJela;
	}

	public Set<PorucenoPice> getPorucenaPica() {
		return porucenaPica;
	}

	public void setPorucenaPica(Set<PorucenoPice> porucenaPica) {
		this.porucenaPica = porucenaPica;
	}

	public Konobar getKonobar() {
		return konobar;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	public Set<Obavestenje> getObavestenja() {
		return obavestenja;
	}

	public void setObavestenja(Set<Obavestenje> obavestenja) {
		this.obavestenja = obavestenja;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}
}
