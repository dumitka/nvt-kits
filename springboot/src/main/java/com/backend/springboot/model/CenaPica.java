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
@Table(name = "cena_pica")
public class CenaPica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pice pice;

    @Column(name = "iznos", nullable = false)
    private float iznos;

    @ManyToMany(mappedBy = "cenePica")
    private Set<KartaPica> kartePica;
}
