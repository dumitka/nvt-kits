package com.backend.springboot.model;

import com.backend.springboot.enums.TipKorisnika;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sef_kuhinje")
public class SefKuhinje extends Kuvar {
    public SefKuhinje() {
        super();
    }

    public SefKuhinje(String korisnickoIme, String lozinka, String ime, String prezime, TipKorisnika tipKorisnika,
                      boolean otpusten) {
        super(korisnickoIme, lozinka, ime, prezime, tipKorisnika, otpusten);
    }
}
