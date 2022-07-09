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

    public List<DayCurrency> getAll() {
        return jdbcTemplate.query("SELECT * FROM DayCurrency",
                new BeanPropertyRowMapper<>(DayCurrency.class));
    }

    public List<DayCurrency> getPeriodCurrencies(Date fromDate, Date toDate, String currencyName) {

        return jdbcTemplate.query("SELECT \"PK_daycur\", value, date, nominal, dc.\"PK_id\"\n" +
                        "FROM \"DayCurrency\" as dc\n" +
                        "join \"Currency\" as c on c.\"PK_id\" = dc.\"PK_id\"\n" +
                        "where name = ? and\n" +
                        "Date between ? and ?;",
                new Object[]{currencyName, fromDate, toDate},
                new BeanPropertyRowMapper<>(DayCurrency.class)).stream().toList();

    }

    public void insert(DayCurrency dayCurrency, String currencyName) {
        Double value = dayCurrency.getValue();
        Date date = dayCurrency.getDate();
        int nominal = dayCurrency.getNominal();
        String pkId = dayCurrency.getPK_id();

        jdbcTemplate.update("INSERT INTO \"DayCurrency\"(value, date, nominal, \"PK_id\")" +
                "VALUES (?, ?, ?, (SELECT \"PK_id\" FROM public.\"Currency\"" +
                        "where name=?));",
                value, date, nominal, currencyName);
    }

}
