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
	private int id;

	@Column(name = "desk_status")
	private DeskStatus deskStatus;

	@Column(name = "tip")
	private float tip;

	@Column(name = "x")
	private float x;

	@Column(name = "y")
	private float y;

	@Column(name = "height")
	private float height;

	@Column(name = "width")
	private float width;

	@Column(name = "reserved")
	private boolean reserved;

}
