package com.backend.springboot.model;

import com.backend.springboot.enums.TipIzvestaja;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "izvestaj")
public class Izvestaj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tip_izvestaja", nullable = false)
	private TipIzvestaja tipIzvestaja;

	@Column(name = "datum", nullable = false)
	private LocalDateTime datum;

	@Column(name = "trosak_na_plate")
	private float trosakNaPlate;

	@Column(name = "trosak_na_nabavke")
	private float trosakNabavke;

	@Column(name = "trosak_na_rezije")
	private float trosakNaRezije;

	@Column(name = "prihod_od_prodaje_jela")
	private float prihodOdProdajeJela;

	@Column(name = "prihod_od_prodaje_pica")
	private float prihodOdProdajePica;

	@Column(name = "baksis")
	private float baksis;

	@ManyToOne
	@JoinColumn(name = "restoran_id", nullable = false)
	private Restoran restoran;
}
