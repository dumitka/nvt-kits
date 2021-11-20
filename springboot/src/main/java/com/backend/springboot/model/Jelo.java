package com.backend.springboot.model;

import com.backend.springboot.enums.TezinaSpremanja;
import com.backend.springboot.enums.TipJela;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jelo")
public class Jelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "naziv", nullable = false)
	private String naziv;

	@Column(name = "tip_jela", nullable = false)
	private TipJela tipJela;

	@Column(name = "opis")
	private String opis;

	@Column(name = "tezina_spremanja")
	private TezinaSpremanja tezinaSpremanja;

	@Column(name = "vreme_spremanja")
	private int vremeSpremanja;

	@Column(name = "kolicina_broj", nullable = false)
	private int kolicinaBroj;

	@Column(name = "kolicina_jedinica", nullable = false)
	private String kolicinaJedinica;

	@Column(name = "slika")
	private String slika;
}
