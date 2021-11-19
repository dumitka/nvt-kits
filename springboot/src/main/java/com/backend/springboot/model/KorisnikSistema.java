package com.backend.springboot.model;

import com.backend.springboot.enums.TipKorisnika;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "korisnik_sistema")
@Inheritance(strategy = InheritanceType.JOINED)
public class KorisnikSistema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "korisnickoIme", nullable = false)
	private String korisnickoIme;

	@Column(name = "lozinka", nullable = false)
	private String lozinka;

	@Column(name = "ime", nullable = false)
	private String ime;

	@Column(name = "prezime", nullable = false)
	private String prezime;

	@Column(name = "tipKorisnika", nullable = false)
	private TipKorisnika tipKorisnika;

	@Column(name = "otpusten", nullable = false)
	private boolean otpusten;

	@OneToMany(mappedBy = "korisnik")
	private Set<Plata> plate;

	public KorisnikSistema() {

	}

	public KorisnikSistema(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
						   boolean otpusten) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.tipKorisnika = tipKorisnika;
		this.otpusten = otpusten;
	}
}
