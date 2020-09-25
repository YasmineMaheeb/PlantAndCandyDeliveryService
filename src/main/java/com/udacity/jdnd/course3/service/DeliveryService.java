package com.udacity.jdnd.course3.service;

import com.udacity.jdnd.course3.model.RecipientAndPrice;
import com.udacity.jdnd.course3.repo.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.model.Delivery;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public RecipientAndPrice getBill(Long deliveryId){
        return deliveryRepository.getBill(deliveryId);
    }

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }
}