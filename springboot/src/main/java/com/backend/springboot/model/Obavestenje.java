package com.backend.springboot.model;

import com.backend.springboot.enums.StatusObavestenja;
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
@Table(name = "obavestenje")
public class Obavestenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    private StatusObavestenja status;

    @Column(name = "poruka", nullable = false)
    private String poruka;

    @ManyToOne(fetch = FetchType.LAZY)
    private Porudzbina porudzbina;
}
