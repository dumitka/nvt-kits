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
	@JoinColumn(name = "sanker_id", nullable = false)
	private Sanker sanker;
}
