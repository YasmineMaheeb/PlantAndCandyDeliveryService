package com.udacity.jdnd.course3.controller;

import com.udacity.jdnd.course3.model.Delivery;
import com.udacity.jdnd.course3.model.RecipientAndPrice;
import com.udacity.jdnd.course3.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @GetMapping("/bill/{deliveryId}")
    public BigDecimal getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId).getPrice();
    }
}