package com.practice.service.dao;

import com.practice.service.model.Currency;
import com.practice.service.model.DayCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.Date;
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

    public List<DayCurrency> getCurrenciesForPeriod(Date fromDate, Date toDate, String currencyName) {
        System.out.println(fromDate);
        return jdbcTemplate.query("SELECT \"PK_daycur\", value, date, nominal, dc.\"PK_id\"\n" +
                        "FROM \"DayCurrency\" as dc\n" +
                        "join \"Currency\" as c on c.\"PK_id\" = dc.\"PK_id\"\n" +
                        "where name = ? and\n" +
                        "Date between ? and ?;",
                new Object[]{currencyName, fromDate, toDate},
                new BeanPropertyRowMapper<>(DayCurrency.class)).stream().toList();

    }

//    public void save(DayCurrency dayCurrency) {
//        jdbcTemplate.update("INSERT INTO DayCurrency VALUES(?, ?, ?, ?)", dayCurrency.get)
//    }

}
