package com.backend.springboot.model;

import com.backend.springboot.enums.DeskStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "desk")
public class Desk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "desk_status")
	private DeskStatus deskStatus;

	@Column(name = "tip")
	private Float tip;

	@Column(name = "x")
	private int x;

	@Column(name = "y")
	private int y;

	@Column(name = "height")
	private float height;

	@Column(name = "width")
	private float width;

	@Column(name = "reserved")
	private Boolean reserved;

	@Column(name = "capacity")
	private Integer capacity;
}
