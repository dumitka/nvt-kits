package com.backend.springboot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
	@JoinColumn(name = "kuvar_id", nullable = false) //mozda ovi idjevi korisnika da se revidiraju
	private Kuvar kuvar;

}
