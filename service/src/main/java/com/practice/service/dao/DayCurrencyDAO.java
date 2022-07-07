package com.practice.service.dao;

import com.practice.service.model.DayCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DayCurrencyDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DayCurrencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DayCurrency> index() {
        return jdbcTemplate.query("SELECT * FROM DayCurrency",
                new BeanPropertyRowMapper<>(DayCurrency.class));
    }

    public DayCurrency get(int PK_id) {
        return jdbcTemplate.query("SELECT * FROM DayCurrency WHERE PK_id=?",
                        new Object[]{PK_id}, new BeanPropertyRowMapper<>(DayCurrency.class))
                .stream().findAny().orElse(null);
    }

//    public void save(DayCurrency dayCurrency) {
//        jdbcTemplate.update("INSERT INTO DayCurrency VALUES(?, ?, ?, ?)", dayCurrency.get)
//    }

}
