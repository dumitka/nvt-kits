package com.backend.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restoran")
public class Restoran {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "restoran")
	private Set<Jelovnik> jelovnici;

	@OneToMany(mappedBy = "restoran")
	private Set<Sto> stolovi;

	@OneToMany(mappedBy = "restoran")
	private Set<KartaPica> kartePica;

	@OneToMany(mappedBy = "restoran")
	private Set<Plata> plate;

	@OneToMany(mappedBy = "restoran")
	private Set<Izvestaj> izvestaji;
}
