package com.udacity.jdnd.course3.repo;


import com.udacity.jdnd.course3.model.Delivery;
import com.udacity.jdnd.course3.model.Plant;
import com.udacity.jdnd.course3.model.RecipientAndPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(long deliveryId) {
        return entityManager.find(Delivery.class, deliveryId);
    }

    public void merge(Delivery delivery) {
        entityManager.merge(delivery);
    }

    public void delete(long deliveryId) {
        Delivery d = entityManager.find(Delivery.class, deliveryId);
        entityManager.remove(d);
    }

    public List<Delivery> findByName(String name) {
        TypedQuery<Delivery> deliveryTypedQuery = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
        deliveryTypedQuery.setParameter("name", name);
        return deliveryTypedQuery.getResultList();
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);
        query.select(
                cb.construct(
                        RecipientAndPrice.class,
                        root.get("delivery").get("name"),
                        cb.sum(root.get("price"))))
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
