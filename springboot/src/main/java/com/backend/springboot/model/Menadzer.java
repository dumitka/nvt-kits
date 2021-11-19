package com.backend.springboot.model;


import com.backend.springboot.enums.TipKorisnika;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "menadzer")
public class Menadzer extends Direktor {
    public Menadzer() {
        super();
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
                    boolean otpusten) {
        super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
    }
}
