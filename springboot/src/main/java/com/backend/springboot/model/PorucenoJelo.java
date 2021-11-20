package com.backend.springboot.model;


import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "poruceno_jelo")
public class PorucenoJelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "kolicina")
	private int kolicina;

	@ManyToOne
	@JoinColumn(name = "jelo_id", nullable = false)
	private Jelo jelo;

	@ManyToOne
	@JoinColumn(name = "porudzbina_id", nullable = false)
	private Porudzbina porudzbina;

	@ManyToOne
	@JoinColumn(name = "korisnik_id", nullable = false)
	private Korisnik kuvar;

}
