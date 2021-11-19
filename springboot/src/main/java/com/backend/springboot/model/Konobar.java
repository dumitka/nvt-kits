package com.backend.springboot.model;


import com.backend.springboot.enums.TipKorisnika;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "konobar")
public class Konobar extends KorisnikSistema {

	@OneToMany(mappedBy = "konobar")
	private Set<Porudzbina> porudzbine; //neka zna svoje porudzbine da bi ih mogao naplatiti

	public Konobar() {
		super();
	}

	public Konobar(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
				   boolean otpusten) {
		super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
	}
}
