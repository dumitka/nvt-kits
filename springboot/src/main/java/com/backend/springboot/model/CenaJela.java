package com.backend.springboot.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cena_jela")
public class CenaJela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jelo jelo;

    @Column(name = "iznos", nullable = false)
    private float iznos;

    @ManyToMany(mappedBy = "ceneJela")
    private Set<Jelovnik> jelovnici;
}
