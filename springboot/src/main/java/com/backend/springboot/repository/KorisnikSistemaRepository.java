package com.backend.springboot.repository;

import com.backend.springboot.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikSistemaRepository extends JpaRepository<Korisnik, Integer> {
}
