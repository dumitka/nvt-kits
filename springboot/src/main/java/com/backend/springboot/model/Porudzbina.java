package com.backend.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "porudzbina")
public class Porudzbina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "porudzbina")
	private Set<PorucenoJelo> porucenaJela;

	@OneToMany(mappedBy = "porudzbina")
	private Set<PorucenoPice> porucenaPica;

	@ManyToOne
	@JoinColumn(name = "korisnik_id", nullable = false)
	private Korisnik konobar;

	@OneToMany(mappedBy = "porudzbina")
	private Set<Obavestenje> obavestenja;

	@ManyToOne
	@JoinColumn(name = "sto_id")
	private Sto sto;

}
