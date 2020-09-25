package com.udacity.jdnd.course3.repo;

import com.udacity.jdnd.course3.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("select p.delivery.completed from Plant p where p.id = :id")
    boolean isDelivered(Long id);

    List<Plant> findByPriceLessThan(BigDecimal price);

    Plant findPlantByName(String name);

}
