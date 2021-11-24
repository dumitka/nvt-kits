package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import com.backend.springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Primary
@Service
public class DrinkService {
    private DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> findAll() { return this.drinkRepository.findAll(); }

    public List<Drink> findAllAvailable() {
        return this.drinkRepository.findAll().stream().filter(pice -> pice.isAvailable()).toList();
    }

    public Drink findOne(int id) { return this.drinkRepository.findById(id).orElse(null); }

    // svako pice jedinstveno odredi naziv i kolicina
    // Jelen 0.5l i Jelen 0.3l mogu da postoje
    public boolean freeNameAndAmount(String name, String amountUnit, double amountNumber) {
        List<Drink> listaPronadjenih = this.drinkRepository.findAll().stream()
                .filter(pice -> pice.getName().equals(name) && pice.getAmountNumber() == amountNumber &&
                        pice.getAmountUnit().equals(amountUnit) && pice.isAvailable()).toList();
        if (listaPronadjenih.isEmpty()) return true;
        return false;
    }

    public Drink save(Drink drink) { return this.drinkRepository.save(drink); }

}
