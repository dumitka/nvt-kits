package com.backend.springboot.model;

import com.backend.springboot.enums.TipPica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pice")
public class Pice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "opis")
    private String opis;

    @Column(name = "kolicina_broj", nullable = false)
    private int kolicinaBroj;

    @Column(name = "kolicina_jedinica", nullable = false)
    private String kolicinaJedinica;

    @Column(name = "tip_pica")
    private TipPica tipPica;

    @Column(name = "slika")
    private String slika;
}
