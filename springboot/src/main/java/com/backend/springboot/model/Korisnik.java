package com.backend.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "korisnik")
public class Korisnik { //implements UserDetails

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "korisnicko_ime", nullable = false)
	private String korisnickoIme;

	@Column(name = "lozinka", nullable = false)
	private String lozinka;

	@Column(name = "ime", nullable = false)
	private String ime;

	@Column(name = "prezime", nullable = false)
	private String prezime;

	@Column(name = "otpusten", nullable = false)
	private boolean otpusten;

	@OneToMany(mappedBy = "korisnik")
	private Set<Plata> plate;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "korisnik_role",
			joinColumns = @JoinColumn(name = "korisnik_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
}
