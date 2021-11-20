package com.backend.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "karta_pica")
public class KartaPica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "pocetak_vazenja", nullable = false)
	private LocalDateTime pocetakVazenja;

	@ManyToMany
	@JoinTable(name = "cene_pica", joinColumns = @JoinColumn(name = "karta_pica_id"), inverseJoinColumns = @JoinColumn(name = "cena_pica_id"))
	private Set<CenaPica> cenePica;

	@ManyToOne
	@JoinColumn(name = "restoran_id", nullable = false)
	private Restoran restoran;
}
