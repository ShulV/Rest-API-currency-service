package com.practice.service.dao;

import com.practice.service.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllCurrencyNames() {
        List<Currency> currencies = jdbcTemplate.query("SELECT name FROM \"Currency\"",
                new BeanPropertyRowMapper<>(Currency.class));
        int currencyAmount = currencies.size();
        List<String> namesOfCurrencies = new ArrayList<>();
        for (Currency currency : currencies) {
            namesOfCurrencies.add(currency.getName());
        }
        return  namesOfCurrencies;
    }

    public List<Currency> index() {
        return jdbcTemplate.query("SELECT * FROM Currency",
                new BeanPropertyRowMapper<>(Currency.class));
    }

    public Currency show(int PK_id) {
        return jdbcTemplate.query("SELECT * FROM Currency WHERE PK_id=?",
                        new Object[]{PK_id}, new BeanPropertyRowMapper<>(Currency.class))
                .stream().findAny().orElse(null);
    }

    public void save(Currency currency) {
        jdbcTemplate.update("INSERT INTO DayCurrency VALUES(?, ?, ?, ?)", currency.getPK_Id(),
                currency.getNumCode(), currency.getCharCode(), currency.getName());
    }

    public void update(int PK_id, Currency updatedCurrency) {
        jdbcTemplate.update("UPDATE Currency SET numcode=?, charcode=?, name=? WHERE PK_id=?",
                updatedCurrency.getNumCode(), updatedCurrency.getCharCode(), updatedCurrency.getName(), PK_id);
    }

    public void delete(int PK_id) {
        jdbcTemplate.update("DELETE FROM Currency WHERE PK_id=?", PK_id);
    }
}
