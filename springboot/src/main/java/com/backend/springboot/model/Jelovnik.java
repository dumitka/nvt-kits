package com.backend.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jelovnik")
public class Jelovnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "pocetak_vazenja", nullable = false)
	private LocalDateTime pocetakVazenja;

	@ManyToMany
	@JoinTable(name = "cene_jela", joinColumns = @JoinColumn(name = "jelovnik_id"), inverseJoinColumns = @JoinColumn(name = "cena_jela_id"))
	private Set<CenaJela> ceneJela;

	@ManyToOne
	@JoinColumn(name = "restoran_id", nullable = false)
	private Restoran restoran;
}
