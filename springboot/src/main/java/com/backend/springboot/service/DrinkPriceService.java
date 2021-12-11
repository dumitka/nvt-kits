package com.backend.springboot.service;

import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.repository.DrinkPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Primary
@Service
public class DrinkPriceService {

    private DrinkPriceRepository drinkPriceRepository;

    @Autowired
    public DrinkPriceService(DrinkPriceRepository drinkPriceRepository) {
        this.drinkPriceRepository = drinkPriceRepository;
    }

    public List<DrinkPrice> findAll() {
        return this.drinkPriceRepository.findAll();
    }

    public DrinkPrice findOne(int id) {
        return this.drinkPriceRepository.findById(id).orElse(null);
    }

    public DrinkPrice save(DrinkPrice drinkPrice) {
        return this.drinkPriceRepository.save(drinkPrice);
    }

    public DrinkPrice findByDrinkAndPrice(int id, float price) {
        List<DrinkPrice> pronadjeneCP = this.drinkPriceRepository.findAll().stream()
                .filter(cp -> cp.getDrink().getId() == id && cp.getPrice() == price).toList();
        if (pronadjeneCP.size() == 1) return pronadjeneCP.get(0);
        return null;
    }
}
