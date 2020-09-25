package com.udacity.jdnd.course3.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.controller.Views;
import org.hibernate.annotations.Nationalized;
import com.udacity.jdnd.course3.model.Delivery;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    private long id;

    @JsonView(Views.plantView.class)
    @Nationalized
    private String name;

    @JsonView(Views.plantView.class)
    @Column(scale = 4, precision = 12)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
