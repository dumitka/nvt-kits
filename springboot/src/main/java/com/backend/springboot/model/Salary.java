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
@Table(name = "salary")
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "amount", nullable = false)
	private float amount;

	//pocetak vazenje plate
	@Column(name = "date_Of_Validation", nullable = false)
	private LocalDateTime dateOfValidation; 

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
