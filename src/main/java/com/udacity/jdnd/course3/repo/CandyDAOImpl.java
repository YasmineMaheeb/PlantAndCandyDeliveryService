package com.udacity.jdnd.course3.repo;

import com.udacity.jdnd.course3.model.CandyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CandyDAOImpl implements CandyDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<CandyData> findALL() {
        String query = "SELECT * FROM CANDY";

        return jdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(CandyData.class));
    }

    @Override
    public void addCandyToDelivery(long candy_id, long delivery_id) {

        String query = "INSERT INTO candy_delivery (candy_id, delivery_id) " +
                "VALUES (:candy_id, :delivery_id)";
        jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("candy_id", candy_id)
                        .addValue("delivery_id", delivery_id));
    }

    @Override
    public List<CandyData> findByDeliveryId(long deliveryId) {
        String query = "SELECT c.* FROM candy c "
                     + "JOIN candy_delivery cd "
                     + "on c.id = cd.candy_id "
                     + "WHERE cd.delivery_id = :delivery_id";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource().addValue("delivery_id", deliveryId),
                new BeanPropertyRowMapper<>(CandyData.class));
    }
}
