package com.backend.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plata")
public class Plata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "iznos", nullable = false)
	private float iznos;

	@Column(name = "pocetak_vazenja", nullable = false)
	private LocalDateTime pocetakVazenja;

	@ManyToOne
	@JoinColumn(name = "korisnik_sistema_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name = "restoran_id", nullable = false)
	private Restoran restoran;
}
