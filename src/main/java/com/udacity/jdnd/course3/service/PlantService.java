package com.udacity.jdnd.course3.service;

import com.udacity.jdnd.course3.model.Plant;
import com.udacity.jdnd.course3.repo.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public long save (Plant plant) {
        Plant p = plantRepository.save(plant);
        return p.getId();
    }

    public boolean isDelivered (long plantId) {
        return plantRepository.isDelivered(plantId);
    }

    public List<Plant> getCheaperPlants (BigDecimal price) {
        return plantRepository.findByPriceLessThan(price);
    }

    public Plant getPlantByName(String name) {
        return plantRepository.findPlantByName(name);
    }
}