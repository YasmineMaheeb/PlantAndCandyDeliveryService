package com.udacity.jdnd.course3.model;


import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;
import org.hibernate.type.YesNoType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@NamedQuery(
        name = "Delivery.findByName",
        query = "Select d from Delivery d where d.name = :name"
)
@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;


    @Column(name = "address_full", length = 500)
    private String address;

    private LocalDateTime deliveryTime;

    @Type(type = "yes_no")
    private boolean completed = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;


    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", completed=" + completed +
                ", plants=" + plants +
                '}';
    }
}
