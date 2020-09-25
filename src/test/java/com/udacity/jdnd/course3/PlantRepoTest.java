package com.udacity.jdnd.course3;

import com.udacity.jdnd.course3.model.Flower;
import com.udacity.jdnd.course3.model.Plant;
import com.udacity.jdnd.course3.model.Delivery;
import com.udacity.jdnd.course3.repo.PlantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class PlantRepoTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void testPriceLessThan (){
        Flower f1 = getFlower();
        f1.setPrice(new BigDecimal(200));
        Flower f2 = getFlower();
        entityManager.persist(f1);
        entityManager.persist(f2);
        List<Plant> plants = plantRepository.findByPriceLessThan(new BigDecimal(150));
        Assertions.assertTrue(plants.size()==1);
        Assertions.assertTrue(plants.get(0).getPrice().compareTo(new BigDecimal(100))==0);
    }

    @Test
    public void testDeliveryCompleted () throws InterruptedException {
        Flower f = getFlower();
        Delivery d = getDelivery();
        f.setDelivery(d);
        List<Plant> plants = new ArrayList<>();
        plants.add(f);
        d.setPlants(plants);
        f = entityManager.persist(f);
        d = entityManager.persist(d);
//        Thread.sleep(1000);
        boolean b = plantRepository.isDelivered(f.getId());
        Assertions.assertTrue(!b);

        d.setCompleted(true);
//        d = entityManager.persist(d);
        b = plantRepository.isDelivered(f.getId());
        Assertions.assertTrue(b);
    }

    public Flower getFlower(){
        Flower f = new Flower();
        f.setColor("yellow");
        f.setName("tulipe");
        f.setPrice(new BigDecimal(100));
        return f;
    }

    public Delivery getDelivery () {
        Delivery d = new Delivery();
        d.setName("Ahmed");
        d.setCompleted(false);
        return d;
    }
}
