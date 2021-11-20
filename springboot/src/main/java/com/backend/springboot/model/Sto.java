package com.backend.springboot.model;

import com.backend.springboot.enums.StatusStola;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sto")
public class Sto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "status")
	private StatusStola statusStola;

	@Column(name = "baksis")
	private float baksis;

	@Column(name = "rezervisan")
	private boolean rezervisan;

	@ManyToOne
	@JoinColumn(name = "restoran_id", nullable = false)
	private Restoran restoran;
}
