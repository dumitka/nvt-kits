package com.backend.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
