package com.backend.springboot.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "poruceno_pice")
public class PorucenoPice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "kolicina")
	private int kolicina;

	@ManyToOne
	@JoinColumn(name = "pice_id", nullable = false)
	private Pice pice;

	@ManyToOne
	@JoinColumn(name = "porudzbina_id", nullable = false)
	private Porudzbina porudzbina;

	@ManyToOne
	@JoinColumn(name = "korisnik_id", nullable = false)
	private Korisnik sanker;
}
