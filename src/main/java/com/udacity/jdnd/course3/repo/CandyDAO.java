package com.udacity.jdnd.course3.repo;

import com.udacity.jdnd.course3.model.CandyData;

import java.util.List;

public interface CandyDAO {

    List<CandyData> findALL ();

    void addCandyToDelivery (long candy_id, long delivery_id);

    List<CandyData> findByDeliveryId (long deliveryId);
}
