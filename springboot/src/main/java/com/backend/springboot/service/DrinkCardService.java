package com.backend.springboot.service;

import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkCard;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.repository.DrinkCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Primary
@Service
public class DrinkCardService {

    private DrinkCardRepository drinkCardRepository;

    @Autowired
    public DrinkCardService(DrinkCardRepository drinkCardRepository) {
        this.drinkCardRepository = drinkCardRepository;
    }

    public List<DrinkCard> findAll() { return this.drinkCardRepository.findAll(); }

    public DrinkCard findOne(int id) { return this.drinkCardRepository.findById(id).orElse(null); }

    public DrinkCard save(DrinkCard drinkCard) { return this.drinkCardRepository.save(drinkCard); }

    public DrinkCard findLatest() {
        List<DrinkCard> sveKartePica = findAll();
        DrinkCard najnovijaKartaPica = null;
        LocalDateTime datum = LocalDateTime.now().minusYears(100);    // verovatno je najnovija mladja od 100 godina
        for (DrinkCard kartaPica : sveKartePica) {
            if (kartaPica.getDateOfValidation().isAfter(datum)) {
                datum = kartaPica.getDateOfValidation();
                najnovijaKartaPica = kartaPica;
            }
        }
        return najnovijaKartaPica;
    }

    public boolean removeDrink(Drink drink) {
        DrinkCard najnovijaKP = findLatest();
        List<DrinkPrice> pronadjenaCena = najnovijaKP.getDrinkPrices().stream()
                .filter(kp -> kp.getDrink().getId() == drink.getId()).toList();
        if (pronadjenaCena.isEmpty()) return false;                             // nije pronadjena cena za to pice
        Set<DrinkPrice> lista = new HashSet<>(najnovijaKP.getDrinkPrices());
        lista.remove(pronadjenaCena.get(0));
        DrinkCard novaKarta = DrinkCard.builder().restaurant(najnovijaKP.getRestaurant())
                .drinkPrices(lista).dateOfValidation(LocalDateTime.now()).build();
        this.drinkCardRepository.save(novaKarta);
        return true;
    }

    public DrinkCard findByDate(LocalDateTime date) {
        List<DrinkCard> listaKP = this.findAll();
        DrinkCard pronadjena = null;
        for (DrinkCard kp : listaKP) {
            if (pronadjena == null) pronadjena = kp;
            else {
                if (kp.getDateOfValidation().isBefore(date)
                        && kp.getDateOfValidation().isAfter(pronadjena.getDateOfValidation())) pronadjena = kp;
            }
        }
        return pronadjena;
    }

    public DrinkPrice findPriceOfDrinkForDate(LocalDateTime date, Integer drinkId) {
        DrinkCard pronadjenaKP = findByDate(date);
        for (DrinkPrice cena : pronadjenaKP.getDrinkPrices()) if (cena.getDrink().getId() == drinkId) return cena;
        return null;
    }

}
