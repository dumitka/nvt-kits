package com.backend.springboot.model;


import com.backend.springboot.enums.TipKorisnika;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "direktor")
public class Direktor extends KorisnikSistema {
    public Direktor() {
        super();
    }

    public Direktor(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
                    boolean otpusten) {
        super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
    }
}
