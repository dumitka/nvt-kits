package com.backend.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
	@JoinColumn(name = "konobar_id", nullable = false)
	private Konobar konobar;

	@OneToMany(mappedBy = "porudzbina")
	private Set<Obavestenje> obavestenja;

	@ManyToOne
	@JoinColumn(name = "sto_id")
	private Sto sto;

}
