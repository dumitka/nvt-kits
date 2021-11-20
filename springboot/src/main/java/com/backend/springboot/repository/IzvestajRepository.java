package com.backend.springboot.repository;

import com.backend.springboot.model.Izvestaj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IzvestajRepository extends JpaRepository<Izvestaj, Integer> {
}
